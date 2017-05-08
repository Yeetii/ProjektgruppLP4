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
import se.chalmers.projektgrupplp4.studentlivinggbg.MainSearchActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    Intent search = new Intent(activity, MainSearchActivity.class);
                    search.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    activity.startActivity(search);
                    return true;
                case R.id.navigation_favorites:
                    return true;
                case R.id.navigation_notifications:
                    Intent searchWatcher = new Intent(activity, SearchWatcherActivity.class);
                    searchWatcher.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    activity.startActivity(searchWatcher);
                    return true;
                case R.id.navigation_settings:
                    Intent settings = new Intent(activity, SettingsActivity.class);
                    settings.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    activity.startActivity(settings);
                    return true;
            }
            return false;
        }

    };

    public FavoritesController(Activity activity, FavoritesView view, FavoritesModel model) {
        this.activity = activity;
        this.view = view;
        this.model = model;
        initNavigationListener();
        initSwipe();
    }

    private void initNavigationListener () {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void onChildDrawController(Canvas c, RecyclerView.ViewHolder viewHolder, float dX,  int actionState) {
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            AccommodationRecyclerViewHolder accommodation = (AccommodationRecyclerViewHolder) viewHolder;
            if((dX > 0 && !accommodation.isFavorite()) || accommodation.isFavorite()) {
                view.draw(c, accommodation.isFavorite(), viewHolder, dX);
            }
        }
    }


    private void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction != ItemTouchHelper.UP && direction != ItemTouchHelper.DOWN){
                    model.updateFavoriteStatus(position, direction != ItemTouchHelper.LEFT, viewHolder);;
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                onChildDrawController(c, viewHolder, dX, actionState);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        attachItemTouchHelperToRecycleView(simpleItemTouchCallback);
    }

    private void attachItemTouchHelperToRecycleView (ItemTouchHelper.SimpleCallback simpleItemTouchCallback) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView((RecyclerView) activity.findViewById(R.id.list));
    }




}
