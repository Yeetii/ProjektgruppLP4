package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import se.chalmers.projektgrupplp4.studentlivinggbg.activity.FavoritesActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.MainSearchActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SettingsActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.MainController;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

/**
 * Created by Jonathan on 09/05/2017.
 */

public class BottomNavigationListener {

    private Context context;
    private int currentSelection;

    private static BottomNavigationListener instance;

    private BottomNavigationListener (Context context) {
        //TODO get activity directly
        this.context = context;
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
            Class newActivityClass = null;

            switch (item.getItemId()) {
                case R.id.navigation_search:
                    if (currentSelection != R.id.navigation_search) {
                        newActivityClass = MainSearchActivity.class;
                    } else return true;
                    break;
                case R.id.navigation_favorites:
                    if (currentSelection != R.id.navigation_favorites) {
                        newActivityClass = FavoritesActivity.class;
                    } else return true;
                    break;
                case R.id.navigation_notifications:
                    if (currentSelection != R.id.navigation_notifications) {
                        newActivityClass = SearchWatcherActivity.class;
                    } else return true;
                    break;
                case R.id.navigation_settings:
                    if (currentSelection != R.id.navigation_settings) {
                        newActivityClass = SettingsActivity.class;
                    } else return true;
                    break;
            }
            if (newActivityClass != null) {
                Intent newActivity = new Intent(context, newActivityClass);
                newActivity.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(newActivity);
                currentSelection = item.getItemId();
                return true;
            }

            return false;
        }

    };
}
