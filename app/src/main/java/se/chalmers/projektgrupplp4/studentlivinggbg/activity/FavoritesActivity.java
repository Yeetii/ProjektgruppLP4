package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.RecyclerViewHelperController;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.FavoritesView;

/**
 * @author
 * Used by: ActivityWithNavigation, activity_favorites.xml,
 * Uses: Db4oDatabase, AccommodationRecyclerViewAdapter, ObjectActivity,
 * Accommodation, FavoritesView
 * Responsibility: Creating the Favorites activity.
 */

public class FavoritesActivity extends ActivityWithNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccommodationRecyclerViewAdapter recyclerViewAdapter = new AccommodationRecyclerViewAdapter(Accommodation.getFavorites(), ObjectActivity.class);
        new FavoritesView(this, recyclerViewAdapter);
        RecyclerViewHelperController recyclerViewHelperController = new RecyclerViewHelperController(this, recyclerViewAdapter);
        recyclerViewHelperController.initSwipe();


        initializeNavigationListener();
    }

    @Override
    protected void onStop() {
        Db4oDatabase.getInstance().saveAllAccommodations();
        super.onStop();
    }



}
