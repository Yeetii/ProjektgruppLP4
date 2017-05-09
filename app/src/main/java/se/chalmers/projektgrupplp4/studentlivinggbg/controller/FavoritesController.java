package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;

import se.chalmers.projektgrupplp4.studentlivinggbg.AccommodationRecyclerViewHolder;
import se.chalmers.projektgrupplp4.studentlivinggbg.BottomNavigationListener;
import se.chalmers.projektgrupplp4.studentlivinggbg.MainSearchActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.RecyclerViewHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.SettingsActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.FavoritesActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.FavoritesModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.FavoritesView;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

/**
 * Created by PG on 07/05/2017.
 */

public class FavoritesController {
    private Activity activity;
    private FavoritesView view;
    private FavoritesModel model;

    public FavoritesController(Activity activity, FavoritesView view, FavoritesModel model) {
        this.activity = activity;
        this.view = view;
        this.model = model;
        RecyclerViewHelper recyclerViewHelper = new RecyclerViewHelper(activity,model);
        recyclerViewHelper.initSwipe();
        initNavigationListener();
        //initSwipe();
    }

    private void initNavigationListener () {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(BottomNavigationListener.getInstance());
    }

}
