package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;

import se.chalmers.projektgrupplp4.studentlivinggbg.view.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.RecyclerViewHelper;

/**
 * Created by PG on 07/05/2017.
 */

public class FavoritesController {


    public FavoritesController(Activity activity, AccommodationRecyclerViewAdapter adapter) {
        RecyclerViewHelper recyclerViewHelper = new RecyclerViewHelper(activity, adapter);
        recyclerViewHelper.initSwipe();
    }

}
