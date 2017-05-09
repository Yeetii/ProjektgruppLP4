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

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.BottomNavigationListener;
import se.chalmers.projektgrupplp4.studentlivinggbg.RecyclerViewHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.FavoritesActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.SettingsActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SearchActivityView;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

/**
 * Created by PG on 28/04/2017.
 */

public class SearchActivityController {
    private static SearchActivityController controller;

    private Activity activity;
    private SearchView searchView;
    private SearchActivityView activityView;
    private SearchActivityModel model;
    private Button showAll;

    public SearchActivityController(Activity activity, SearchActivityModel model, SearchActivityView activityView) {
        this.activity = activity;
        this.model = model;
        this.activityView = activityView;
        RecyclerViewHelper recyclerViewHelper = new RecyclerViewHelper(activity,model);
        recyclerViewHelper.initSwipe();
        initListeners();
        //initSwipe();
        controller = this;
    }

    private void initListeners() {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        ImageButton advancedSearch = (ImageButton) activity.findViewById(R.id.advancedSearch);
        searchView = (SearchView) activity.findViewById(R.id.searchField);
        showAll = (Button) activity.findViewById(R.id.showAllButton);

        showAll.setOnClickListener(onClickShowAll);
        advancedSearch.setOnClickListener(onClickAdvancedSearch);
        navigation.setOnNavigationItemSelectedListener(BottomNavigationListener.getFirstInstance(activity));
        searchView.setIconifiedByDefault(true);
        searchView.setOnClickListener(onClickListener);
        searchView.setOnQueryTextListener(onQueryTextListener);
    }

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

    public static void updateAccommodations(final List<Accommodation> accommodations) {
        if (controller != null) {
            controller.activity.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      Accommodation.setNewAccommodationList(accommodations, controller.activity.getApplicationContext());
                      controller.model.refreshAdapter();
                  }
              }
            );
        }
    }


}
