package se.chalmers.projektgrupplp4.studentlivinggbg.Model;


import java.util.ArrayList;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

public class MainModel {

    private static MainModel mainModel;

    private SearchHandler searchHandler;

    private Settings settings;

    private ArrayList<Accommodation> accommodations = new ArrayList<>();

    public static MainModel getReference() {
        if (mainModel == null) {
            mainModel = new MainModel();
        }
        return mainModel;
    }

    private MainModel () {
        searchHandler = new SearchHandler();
        settings = new Settings();
        updateAccommodations();

    }

    public ArrayList<Accommodation> getFavorites() {
        ArrayList<Accommodation> result = new ArrayList<>();
        for (Accommodation accommodation: accommodations){
            if(accommodation.getFavorite()) {
                result.add(accommodation);
            }
        }
        return result;
    }

    public void updateAccommodations () {
        //TODO Use database to update accommodations

        accommodations.add(new Accommodation("Lindholmsallén 37 Läg 101", AccommodationHouseType.TWO_ROOMS, 3650, 16.4, 120, R.drawable.house_image1));
        accommodations.add(new Accommodation("ViktorRydbersgatan 48 Läg 1208", AccommodationHouseType.CORRIDOR, 3650, 40, 0, R.drawable.house_image2));
        accommodations.add(new Accommodation("Våxtorpsgatan 00 Läg 1337", AccommodationHouseType.FOUR_ROOMS, 12345, 99, 1200, R.drawable.house_image3));

    }

    public ArrayList<Accommodation> getAccommodations(){return MainModel.getReference().accommodations;}
}
