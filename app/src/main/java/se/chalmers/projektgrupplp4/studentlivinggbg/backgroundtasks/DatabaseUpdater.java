package se.chalmers.projektgrupplp4.studentlivinggbg.backgroundtasks;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import se.chalmers.projektgrupplp4.studentlivinggbg.AccommodationAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.ChalmersAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.CreateDrawableHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.database.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.NetworkHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.RequestAccommodations;
import se.chalmers.projektgrupplp4.studentlivinggbg.SGSAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.imagemodel.ImageModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;

/**
 * Created by PG on 12/05/2017.
 */

class DatabaseUpdater implements Observer {
    private static final DatabaseUpdater INSTANCE = new DatabaseUpdater();
    private int counter = 0;
    private Context context;
    private Db4oDatabase db;
    private List<Accommodation> sgsAccommodations;
    private List<Accommodation> chalmersAccommodations;


    static DatabaseUpdater getInstance() {return INSTANCE;}

    void update(Context context) {
        this.context = context;
        db = Db4oDatabase.getInstance().initDataBase(context);
        Long lastUpdateTime = db.getTimestamp();

        if (lastUpdateTime == null || lastUpdateTime > System.currentTimeMillis() ||
                checkIfItShouldUpdate(lastUpdateTime)) {
            getNewData(context);
        } else {
            AlarmTimeManger.getInstance().createNextAlarm(context);
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
        return lastUpdateHour < 18 && currentHour >= 18;
    }

    private void notifyApp(List<Accommodation> accommodations, Context context) {
        SearchActivityController.updateAccommodations(accommodations);
    }

    private void getNewData (Context context) {
        RequestAccommodations sgsRequest = new RequestAccommodations(true, context, this);
        RequestAccommodations chalmersRequest = new RequestAccommodations(false, context, this);
        sgsRequest.execute();
        chalmersRequest.execute();
    }

    private List<Accommodation> fillNewAccommodations(Context context, String inputString) {
        List<Accommodation> newAccommodations;
        if (inputString.equals("SGS")) {
            AccommodationAdapter sgsAdapter = AccommodationAdapter.getPopulatedAdapter(SGSAdapter.class, context, "SGSData");
            newAccommodations = sgsAdapter.getAccommodations();
            sgsAccommodations = newAccommodations;
        } else {
            AccommodationAdapter chalmersAdapter = AccommodationAdapter.getPopulatedAdapter(ChalmersAdapter.class, context, "ChalmersData");
            newAccommodations = chalmersAdapter.getAccommodations();
            chalmersAccommodations = newAccommodations;
            getAndSetAmountOfSearchersChalmers(chalmersAccommodations);
        }
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

    private void setAccommodations (String inputString) {
        //Do as much as possible with each request.
        List<Accommodation> previousAccommodations = db.findAll(Accommodation.class);
        List<Accommodation> newAccommodations = fillNewAccommodations(context, inputString);

        Accommodation.transferFavoriteStatus(previousAccommodations, newAccommodations);
        ImageModel<Drawable> imageModel = ImageModel.getInstance();
        imageModel.setHelper(new CreateDrawableHelper(context));
        imageModel.getAndSaveImages(false, newAccommodations);

        counter++;
        //Only do some things when both SGS and Chalmers are done.
        if (counter % 2 == 1) return;
        newAccommodations = new ArrayList<>();
        newAccommodations.addAll(chalmersAccommodations);
        newAccommodations.addAll(sgsAccommodations);
        db.replaceAccommodationsList(newAccommodations);


        //SearchWatcher stuff
        SearchWatcherModel.getSearchWatcherItems().clear();
        SearchWatcherModel.getSearchWatcherItems().addAll(db.<SearchWatcherItem>findAll(SearchWatcherItem.class));
        //Gets accommodations that weren't in the old database
        List<Accommodation> uniqueNewAccommoadations = new ArrayList<Accommodation>(newAccommodations);
        uniqueNewAccommoadations.removeAll(previousAccommodations);

        int mathces = SearchWatcherModel.updateWatchers(uniqueNewAccommoadations);

        if (mathces > 0){
            NotificationSender.sendNotification(context, mathces);
        }


        notifyApp(newAccommodations, context);

        AlarmTimeManger.getInstance().createNextAlarm(context);
        db.close();

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

    @Override
    public void update(String update) {
        setAccommodations(update);
    }
}
