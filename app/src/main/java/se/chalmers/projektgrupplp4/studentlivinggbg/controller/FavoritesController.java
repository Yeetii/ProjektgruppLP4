package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.support.design.widget.BottomNavigationView;

import se.chalmers.projektgrupplp4.studentlivinggbg.view.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.BottomNavigationListener;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.RecyclerViewHelper;

/**
 * Created by PG on 07/05/2017.
 */

public class FavoritesController {
    private Activity activity;


    public FavoritesController(Activity activity, AccommodationRecyclerViewAdapter adapter) {
        this.activity = activity;
        RecyclerViewHelper recyclerViewHelper = new RecyclerViewHelper(activity, adapter);
        recyclerViewHelper.initSwipe();
        initNavigationListener();
    }

    private void initNavigationListener () {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(BottomNavigationListener.getInstance());
    }

}
