package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.ImageModel;

/**
 * Created by PG on 07/05/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Update accommodations received");
        updateDatabase(context);
    }

    private void updateDatabase(Context context) {
        long updateInterval = AlarmManager.INTERVAL_DAY / 2; //Should update every 12h
        long extraDelayTime = 3 * 1000; //Set 3s delay, might be need

        Db4oDatabase db = initDataBase(context);
        Long lastUpdateTime = db.getTimestamp();

        if (lastUpdateTime == null || lastUpdateTime > System.currentTimeMillis() ||
                checkIfItShouldUpdate(lastUpdateTime)) {
            System.out.println("Fetching new data");
            List<Accommodation> previousAccommodations = db.findAll();

            getNewData(context);

            List<Accommodation> newAccommodations = fillNewAccommodations(context);

            updateFavoriteStatus(previousAccommodations, newAccommodations);
            storeNewData(db, newAccommodations);
            ImageModel.getInstance().getAndSaveImages(false, newAccommodations, context);
            createNextAlarm(System.currentTimeMillis() +  updateInterval + extraDelayTime, context);
            notifyApp(newAccommodations, context);
        } else {
            //Set next alarm so it calls 12h + 3s after last update of the database.
            long nextUpdateTime = nextUpdateTime(lastUpdateTime);
            System.out.println("Set up next fetch at: " + nextUpdateTime);
            createNextAlarm(nextUpdateTime , context);
            db.close();
        }
    }

    private boolean checkIfItShouldUpdate(long lastUpdate) {
        /*
            The application should update at 6, 12, 18, 24 a clock.
         */
        //Don't want to struggle with month/year. Every check should appear at least every 6h.
        if (6 * 3600 * 1000 + lastUpdate < System.currentTimeMillis()) return true;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Etc/GMT+2"));
        calendar.setTimeInMillis(lastUpdate);
        //Since it is a singleton we have to save all the info and then change.
        int lastUpdateDay = calendar.get(Calendar.DATE);
        int lastUpdateHour = calendar.get(Calendar.HOUR);

        calendar.setTimeInMillis(System.currentTimeMillis());

        int currentDay = calendar.get(Calendar.DATE);
        int currentHour = calendar.get(Calendar.HOUR);
        if (lastUpdateDay != currentDay) return true;
        if (lastUpdateHour < 6 && currentHour >= 6) return true;
        if (lastUpdateHour < 12 && currentHour >= 12) return true;
        if (lastUpdateHour < 18 && currentHour >= 18) return true;
        return false;
    }

    private long nextUpdateTime(long lastUpdateTime) {
        Calendar calender = Calendar.getInstance();
        calender.setTimeZone(TimeZone.getTimeZone("Etc/GMT+2"));
        calender.setTimeInMillis(System.currentTimeMillis());
        int currentHour = calender.get(Calendar.HOUR);
        calender.setTimeInMillis(lastUpdateTime);
        int amountOfHours = 0;
        int amountOfMin = 0;
        if (calender.get(Calendar.MINUTE) % 60 != 0) {
            amountOfHours--;
            amountOfMin = 60 - calender.get(Calendar.MINUTE);
        }

        if (calender.get(Calendar.HOUR) % 6 != 0) {
            amountOfHours += 6 - (calender.get(Calendar.HOUR) % 6);
        } else if (calender.get(Calendar.HOUR) == currentHour) {
            amountOfHours += 6;
        }
        return lastUpdateTime + 1000 * (3600 * amountOfHours + 60 * amountOfMin);
    }

    //TODO: create notifyApp method
    private void notifyApp(List<Accommodation> accommodations, Context context) {
        SearchActivityController.updateAccommodations(accommodations);
    }

    private void createNextAlarm(long nextUpdateItem, Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent createdIntent = new Intent(context, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, createdIntent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, nextUpdateItem, pendingIntent);
    }

    private void getNewData (Context context) {
        RequestAccommodations sgsRequest = new RequestAccommodations(true, context);
        RequestAccommodations chalmersRequest = new RequestAccommodations(false, context);
        sgsRequest.execute();
        chalmersRequest.execute();

        while (!sgsRequest.isDone() || !chalmersRequest.isDone()) {
            //Shit code, please fix :)
        }
    }

    private List<Accommodation> fillNewAccommodations(Context context) {
        List<Accommodation> newAccommodations;
        AccommodationAdapter sgsAdapter = AccommodationAdapter.getPopulatedAdapter(SGSAdapter.class, context);
        AccommodationAdapter chalmersAdapter = AccommodationAdapter.getPopulatedAdapter(ChalmersAdapter.class, context);
        newAccommodations = sgsAdapter.getAccommodations();
        newAccommodations.addAll(chalmersAdapter.getAccommodations());
        return newAccommodations;
    }

    private void storeNewData (Db4oDatabase db, List<Accommodation> newAccommodations) {
        db.deleteAll();
        db.storeTimestamp();
        for (Accommodation accommodation: newAccommodations) {
            db.store(accommodation);
        }
        db.close();
    }

    private void updateFavoriteStatus (List<Accommodation> previousAccommodations,
                                       List<Accommodation> newAccommodations) {
        for (int i = 0; i < previousAccommodations.size(); i++) {
            for (int y = 0; y < newAccommodations.size(); y++) {
                Accommodation previousAccommodation = previousAccommodations.get(i);
                Accommodation newAccommodation = newAccommodations.get(i);

                if (previousAccommodation.getObjectNumber().equals(newAccommodation.getObjectNumber())) {
                    newAccommodation.setFavorite(previousAccommodation.getFavorite());
                    break;
                }
            }
        }
    }

    private Db4oDatabase initDataBase(Context context) {
        Db4oDatabase db = Db4oDatabase.getInstance();
        db.setContext(context);
        return db;
    }
}
