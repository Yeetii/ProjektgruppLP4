package se.chalmers.projektgrupplp4.studentlivinggbg.Model;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.Observable;
import se.chalmers.projektgrupplp4.studentlivinggbg.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;

public class MainModel {

    private static MainModel INSTANCE = new MainModel();

    private SearchHandler searchHandler;

    private Settings settings;

    private List<Accommodation> accommodations = new ArrayList<>();

    public static MainModel getInstance() {
        return INSTANCE;
    }

    private MainModel () {
        searchHandler = new SearchHandler();
        settings = new Settings();
        loadDatabase();
    }
    public static Thread dbThread;
    private void loadDatabase() {
        //Throws error if not done in a thread.
        dbThread = (new Thread() {

            @Override
            public void run() {
                Db4oDatabase db = Db4oDatabase.getInstance();

                //Temp, just fill it the first time
                if (db.findAll().size() == 0) {
                    db.store(new Accommodation("Lindholmsallén 37 Läg 101", AccommodationHouseType.TWO_ROOMS, 3650, 16.4, 120, R.drawable.house_image1));
                    db.store(new Accommodation("ViktorRydbersgatan 48 Läg 1208", AccommodationHouseType.CORRIDOR, 3650, 40, 0, R.drawable.house_image2));
                    db.store(new Accommodation("Våxtorpsgatan 00 Läg 1337", AccommodationHouseType.FOUR_ROOMS, 12345, 99, 1200, R.drawable.house_image3));
                }

                List<Accommodation> temp = db.findAll();
                accommodations.clear();
                accommodations.addAll(temp);
                //nortifyObservers();
                db.close();
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

}
