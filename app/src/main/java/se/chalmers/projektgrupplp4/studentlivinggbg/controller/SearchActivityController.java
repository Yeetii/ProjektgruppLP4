package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import se.chalmers.projektgrupplp4.studentlivinggbg.SettingsActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.FavoritesActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SearchActivityView;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

/**
 * Created by PG on 28/04/2017.
 */

public class SearchActivityController {
    private Activity activity;
    private Context context;
    private SearchView searchView;
    private SearchActivityView activityView;
    private SearchActivityModel model;
    private Button showAll;

    public SearchActivityController(Activity activity, SearchActivityModel model, SearchActivityView activityView) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.model = model;
        this.activityView = activityView;
        initListeners();
        initSwipe();
    }

    private void initListeners() {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        ImageButton advancedSearch = (ImageButton) activity.findViewById(R.id.advancedSearch);
        searchView = (SearchView) activity.findViewById(R.id.searchField);
        showAll = (Button) activity.findViewById(R.id.showAllButton);

        showAll.setOnClickListener(onClickShowAll);
        advancedSearch.setOnClickListener(onClickAdvancedSearch);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        searchView.setIconifiedByDefault(true);
        searchView.setOnClickListener(onClickListener);
        searchView.setOnQueryTextListener(onQueryTextListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    return true;
                case R.id.navigation_favorites:
                    Intent favorites = new Intent(activity, FavoritesActivity.class);
                    favorites.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    activity.startActivity(favorites);
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

    private SearchView.OnClickListener onClickListener = new SearchView.OnClickListener() {
        @Override
        public void onClick(View v) {
            searchView.setIconified(false);
        }
    };




    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener(){

        @Override
        public boolean onQueryTextSubmit(String query) {
            SearchHandler.createSearch(query);
            model.refreshAdapter();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };


    //Show all button
    private Button.OnClickListener onClickShowAll = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            model.setLastSearch(SearchHandler.createSearch(""));
            model.refreshNotUpdateAdapter();
        }
    };


    //Advanced search button
    private ImageButton.OnClickListener onClickAdvancedSearch = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            activityView.openAdvancedSearch();
        }
    };

    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                model.changeFavorite(viewHolder, direction);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                activityView.displayChangeFavorite(actionState, dX, c, viewHolder);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(model.getRecyclerView());
    }


}
