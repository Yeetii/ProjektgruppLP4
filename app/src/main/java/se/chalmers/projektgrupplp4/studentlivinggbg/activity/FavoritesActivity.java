package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import se.chalmers.projektgrupplp4.studentlivinggbg.view.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.FavoritesController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.FavoritesView;

public class FavoritesActivity extends ActivityWithNavigation {
    AccommodationRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerViewAdapter = new AccommodationRecyclerViewAdapter(Accommodation.getFavorites(), ObjectActivity.class);
        new FavoritesView(this, recyclerViewAdapter);
        new FavoritesController(this, recyclerViewAdapter);
        initializeNavigationListener();
    }

    @Override
    protected void onResume () {
        recyclerViewAdapter.clear();
        recyclerViewAdapter.addAll(Accommodation.getFavorites());
        super.onResume();
    }



}
