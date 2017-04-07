package se.chalmers.projektgrupplp4.studentlivinggbg.Model;

import java.util.List;

public class MainModel {

    private static MainModel mainModel;

    private SearchHandler searchHandler;

    private Settings settings;

    private List accommodations;

    public static MainModel getReference () {
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

    public void updateAccommodations () {
        //TODO Use database to update accommodations
    }
}
