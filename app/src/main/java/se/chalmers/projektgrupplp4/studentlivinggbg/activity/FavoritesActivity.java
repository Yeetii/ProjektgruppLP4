package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import se.chalmers.projektgrupplp4.studentlivinggbg.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.FavoritesController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.FavoritesView;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccommodationRecyclerViewAdapter recyclerViewAdapter = new AccommodationRecyclerViewAdapter(Accommodation.getFavorites(), getApplicationContext());
        new FavoritesView(this, recyclerViewAdapter);
        new FavoritesController(this, recyclerViewAdapter);
    }



}
