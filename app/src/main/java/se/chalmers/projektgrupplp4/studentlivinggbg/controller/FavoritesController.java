package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;

import se.chalmers.projektgrupplp4.studentlivinggbg.view.AccommodationRecyclerViewAdapter;

/**
 * @author Peter
 */

public class FavoritesController {


    public FavoritesController(Activity activity, AccommodationRecyclerViewAdapter adapter) {
        RecyclerViewHelperController recyclerViewHelperController = new RecyclerViewHelperController(activity, adapter);
        recyclerViewHelperController.initSwipe();
    }

}
