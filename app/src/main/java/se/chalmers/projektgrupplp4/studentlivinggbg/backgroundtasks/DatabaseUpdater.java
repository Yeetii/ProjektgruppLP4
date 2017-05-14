package se.chalmers.projektgrupplp4.studentlivinggbg.backgroundtasks;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import se.chalmers.projektgrupplp4.studentlivinggbg.AccommodationAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.ChalmersAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.NetworkHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.RequestAccommodations;
import se.chalmers.projektgrupplp4.studentlivinggbg.SGSAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.ImageModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;

/**
 * Created by PG on 12/05/2017.
 */

class DatabaseUpdater {
    private static final DatabaseUpdater INSTANCE = new DatabaseUpdater();

    static DatabaseUpdater getInstance() {return INSTANCE;}

    void update(Context context) {
        Db4oDatabase db = Db4oDatabase.getInstance().initDataBase(context);
        Long lastUpdateTime = db.getTimestamp();

        if (lastUpdateTime == null || lastUpdateTime > System.currentTimeMillis() ||
                checkIfItShouldUpdate(lastUpdateTime)) {
            List<Accommodation> previousAccommodations = db.findAllAccommodations();

            getNewData(context);

            List<Accommodation> newAccommodations = fillNewAccommodations(context);

            Accommodation.transferFavoriteStatus(previousAccommodations, newAccommodations);
            db.replaceAccommodationsList(newAccommodations);
            ImageModel.getInstance().getAndSaveImages(false, newAccommodations, context);

            //SearchWatcher stuff
            //Gets accommodations that weren't in the old database
            List<Accommodation> uniqueNewAccommoadations = new ArrayList<Accommodation>(newAccommodations);
            uniqueNewAccommoadations.removeAll(previousAccommodations);

            int mathces = SearchWatcherModel.updateWatchers(uniqueNewAccommoadations);
            NotificationSender.sendNotification(context, mathces);


            notifyApp(newAccommodations, context);
        }
        AlarmTimeManger.getInstance().createNextAlarm(context);
        db.close();
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

    private void notifyApp(List<Accommodation> accommodations, Context context) {
        SearchActivityController.updateAccommodations(accommodations);
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
        List<Accommodation> chalmersAccommodations;

        AccommodationAdapter sgsAdapter = AccommodationAdapter.getPopulatedAdapter(SGSAdapter.class, context);
        AccommodationAdapter chalmersAdapter = AccommodationAdapter.getPopulatedAdapter(ChalmersAdapter.class, context);
        newAccommodations = sgsAdapter.getAccommodations();
        chalmersAccommodations = chalmersAdapter.getAccommodations();

        getAndSetAmountOfSearchersChalmers(chalmersAccommodations);

        newAccommodations.addAll(chalmersAccommodations);
        return newAccommodations;
    }

    private void getAndSetAmountOfSearchersChalmers(final List<Accommodation> accommodations) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < accommodations.size(); i++) {
            final int finalInt = i;
            Thread newThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    StringBuffer buffer = NetworkHelper.requestChalmersAccommodation(accommodations.get(finalInt));
                    setSearcherChalmers(buffer, accommodations.get(finalInt));
                }
            });
            newThread.start();
            threads.add(newThread);
        }
        //We have to wait until all request are set.
        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void setSearcherChalmers(StringBuffer stringBuffer, Accommodation accommodation) {
        int amount = 0;
        String response = stringBuffer.toString();
        String findString = "intresseanmälts av ";
        if (response.contains(findString)) {
            response = response.substring(response.indexOf(findString) + findString.length());
            response = response.substring(0, response.indexOf(" "));
            amount = Integer.parseInt(response);
        }
        accommodation.setSearchers(amount);
    }
}
