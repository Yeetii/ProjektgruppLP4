package se.chalmers.projektgrupplp4.studentlivinggbg.Model;

import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.Controller.MainController;
import se.chalmers.projektgrupplp4.studentlivinggbg.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.GSONAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.SearchWatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.SendPostSGS;

public class MainModel {

    private static MainModel INSTANCE = new MainModel();
    public static Thread dbThread;


    private SearchHandler searchHandler;

    private Settings settings;

    private List<Accommodation> accommodations = new ArrayList<>();
    private List<SearchWatcherItem> searchWatcherItems = new ArrayList<>();

    public static MainModel getInstance() {
        return INSTANCE;
    }

    private MainModel () {
        searchHandler = new SearchHandler();
        settings = new Settings();
        loadDatabase();
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

        Search search1 = new Search("testGlobal", "Viktor Rydbergsgatan", "syd",
                testHouseType, testHost,
                -1, 9998, 100, 200, 9999,
                "13-12-17", "24-12-17");

        Search search2 = new Search("testGlobal2", "Lindholmsallén", "norr",
                testHouseType, testHost,
                700, 9998, 100, 450, 9999,
                "13-12-17", "24-12-17");

        Search search3 = new Search("testGlobal2", "Gibraltargatan", "öst",
                testHouseType2, testHost,
                700, -1, 100, -1, 9999,
                "13-12-17", "24-12-17");

        searchWatcherItems.add(new SearchWatcherItem("Gamla boendet", search1));
        searchWatcherItems.add(new SearchWatcherItem("Nära masters", search2));
        searchWatcherItems.add(new SearchWatcherItem("Lättast att få", search3));
    }

    public List<SearchWatcherItem> getSearchWatcherItems(){return INSTANCE.searchWatcherItems;}



    private void loadDatabase() {
        //Throws error if not done in a thread.
        dbThread = (new Thread() {

            @Override
            public void run() {
                int twelveHours = 12 * 3600 * 1000;
                Db4oDatabase db = Db4oDatabase.getInstance();
                List<Accommodation> temp = db.findAll();

                accommodations.clear();

                //legacy reasons, should be removed once everyone has used this method once.
                if (temp.size() > 0 && temp.get(0).getObjectNumber() == null) {
                    db.deleteAll();
                } else if (db.getTimestamp() == null || Math.abs(db.getTimestamp() -
                        System.currentTimeMillis()) > twelveHours) {
                    //Above handles ege case if time is changed.


                    db.deleteAll();
                    db.storeTimestamp();
                    SendPostSGS sendPostSGS = new SendPostSGS();
                    sendPostSGS.execute();
                    while (!sendPostSGS.isDone()) {
                        //Shit code, please fix :)
                    }
                } else {
                    accommodations.addAll(temp);
                }
                db.close();

                GSONAdapter adapter = getPopulatedGsonAdapter();
                adapter.updateAccommodations();
                Long currentTime = System.currentTimeMillis();
                ImageModel.getInstance().loadAllImages();
                System.out.println("Find timestamp: " + (System.currentTimeMillis() - currentTime));
            }
        });

        dbThread.start();
    }

    public void save() {
        Db4oDatabase db = Db4oDatabase.getInstance();
        db.deleteAll();
        for (int i = 0; i < accommodations.size(); i++) {
            db.store(accommodations.get(i));
        }
        db.close();
    }

    public ArrayList<Accommodation> getFavorites() {
        System.out.println(accommodations);
        ArrayList<Accommodation> result = new ArrayList<>();
        for (Accommodation accommodation: accommodations){
            System.out.println("accommodations: " + accommodations);
            if(accommodation.getFavorite()) {
                result.add(accommodation);
            }
        }
        return result;
    }

    public List<Accommodation> getAccommodations(){return INSTANCE.accommodations;}


    /*
        Creates a Gson Adapter filled with info from a JSON file.
    */
    private GSONAdapter getPopulatedGsonAdapter() {
        Gson gson = new Gson();
        GSONAdapter adapter = null;
        try {
            /*
            MainController.applicationContext does not follow MVC. But this should be temp code
            anyway.
             */
            AssetManager am = MainController.applicationContext.getAssets();

            InputStream is = MainController.applicationContext.openFileInput("SGSData");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            adapter = gson.fromJson(reader, GSONAdapter.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return adapter;
    }

}
