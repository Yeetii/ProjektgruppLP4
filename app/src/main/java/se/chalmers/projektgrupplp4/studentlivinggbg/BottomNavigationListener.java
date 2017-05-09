package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import se.chalmers.projektgrupplp4.studentlivinggbg.activity.FavoritesActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherActivity;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

/**
 * Created by Jonathan on 09/05/2017.
 */

public class BottomNavigationListener {

    Activity activity;
    int currentSelection;

    private static BottomNavigationListener instance;

    private BottomNavigationListener (Activity activity) {
        //TODO get activity directly
        this.activity = activity;
        this.currentSelection = R.id.navigation_search;
    }

    public static BottomNavigationView.OnNavigationItemSelectedListener getFirstInstance (Activity activity) {
        if (instance == null) {
            instance = new BottomNavigationListener(activity);
            return instance.mOnNavigationItemSelectedListener;
        } else {
            return instance.mOnNavigationItemSelectedListener;
        }
    }

    public static BottomNavigationView.OnNavigationItemSelectedListener getInstance () {
        if (instance == null) {
            return null;
        } else {
            return instance.mOnNavigationItemSelectedListener;
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    if (currentSelection != R.id.navigation_search) {
                        Intent search = new Intent(activity, MainSearchActivity.class);
                        search.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        activity.startActivity(search);
                        currentSelection = R.id.navigation_search;
                    }
                    return true;
                case R.id.navigation_favorites:
                    if (currentSelection != R.id.navigation_favorites) {
                        Intent favorites = new Intent(activity, FavoritesActivity.class);
                        favorites.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        activity.startActivity(favorites);
                        currentSelection = R.id.navigation_favorites;
                    }
                    return true;
                case R.id.navigation_notifications:
                    if (currentSelection != R.id.navigation_notifications) {
                        Intent searchWatcher = new Intent(activity, SearchWatcherActivity.class);
                        searchWatcher.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        activity.startActivity(searchWatcher);
                        currentSelection = R.id.navigation_notifications;
                    }
                    return true;
                case R.id.navigation_settings:
                    if (currentSelection != R.id.navigation_settings) {
                        Intent settings = new Intent(activity, SettingsActivity.class);
                        settings.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        activity.startActivity(settings);
                        currentSelection = R.id.navigation_settings;
                    }
                    return true;
            }
            return false;
        }

    };
}
