package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.backgroundTasks.AlarmTimeManger;
import se.chalmers.projektgrupplp4.studentlivinggbg.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;

public class MainModel {
    //Does this even need to be a model anymore? I don't think so.
    //Rename to startUpProcess/Init program or something?

    private static MainModel INSTANCE = new MainModel();
    public static Thread dbThread;


    private SearchHandler searchHandler;

    private Settings settings;

    private List<SearchWatcherItem> searchWatcherItems = new ArrayList<>();

    public static MainModel getInstance() {
        return INSTANCE;
    }

    private MainModel () {
        searchHandler = new SearchHandler();
        settings = new Settings();
        //TODO: This is just for testing
        initializeTestSearchWatchers();

    }

    private void initializeTestSearchWatchers() {

        ArrayList<AccommodationHouseType> testHouseType = new ArrayList<>();
        testHouseType.add(AccommodationHouseType.COOKING_CABINET);

        ArrayList<AccommodationHouseType> testHouseType2 = new ArrayList<>();
        testHouseType2.add(AccommodationHouseType.FOUR_ROOMS);

        ArrayList<AccommodationHost> testHost = new ArrayList<>();
        testHost.add(AccommodationHost.SGS);

        ArrayList<Region> testRegion = new ArrayList<>();
        testRegion.add(Region.NORTH);

        Search search1 = new Search("Bra");

        Search search2 = new Search("Bra", "",
                testHouseType, testHost, testRegion,
                700, 9998, 100, 450, 9999,
                "13-12-17", "24-12-17");

        Search search3 = new Search("Omg", "Gibraltargatan",
                testHouseType2, testHost, testRegion,
                700, -1, 100, -1, 9999,
                "13-12-17", "24-12-17");


        searchWatcherItems.add(new SearchWatcherItem("Gamla boendet", search1));
        searchWatcherItems.add(new SearchWatcherItem("Nära masters", search2));
        searchWatcherItems.add(new SearchWatcherItem("Lättast att få", search3));
    }

    public List<SearchWatcherItem> getSearchWatcherItems(){return INSTANCE.searchWatcherItems;}



    public void loadDatabase(final Context context) {
        //Throws error if not done in a thread.
        dbThread = (new Thread() {

            @Override
            public void run() {
                Db4oDatabase db = Db4oDatabase.getInstance();
                List<Accommodation> temp = db.findAll();
                //Should happen the first time the user starts the app
                if (temp.size() == 0) {
                    AlarmTimeManger.getInstance().setUpInstantAlarm(context);
                }

                Accommodation.getAccommodations().clear();
                Accommodation.getAccommodations().addAll(removeNullFrom(temp));
                db.close();

                Long currentTime = System.currentTimeMillis();
                ImageModel.getInstance().getAndSaveImages(true, Accommodation.getAccommodations(), context);
                System.out.println("Find timestamp: " + (System.currentTimeMillis() - currentTime));
            }
        });

        dbThread.start();
    }

    private List<Accommodation>  removeNullFrom(List<Accommodation> temp) {
        List<Accommodation> tempResult = new ArrayList<>();

        for(Accommodation looper : temp){
            try{
            if(looper != null && !(looper.getAddress().equals(""))){
                tempResult.add(looper);
            }}
            catch(NullPointerException e){}
        }
        return tempResult;
    }

    public void save() {
        Db4oDatabase db = Db4oDatabase.getInstance();
        db.deleteAll();
        for (int i = 0; i < Accommodation.getAccommodations().size(); i++) {
            db.store(Accommodation.getAccommodations().get(i));
        }
        db.close();
    }
}
