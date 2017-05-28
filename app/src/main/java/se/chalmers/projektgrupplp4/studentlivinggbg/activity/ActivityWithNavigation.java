package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import se.chalmers.projektgrupplp4.studentlivinggbg.service.ActivitySwitcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * @author Peter Gärdenäs
 * Used by: AdvancedSearchActivity, FavoritesActivity, MainSearchActivity, ObjectActivity,
 * SearchWatcherActivity, SettingsActivity
 * Uses: ActivitySwitcher, MainSearchActivity, SettingsActivity, FavoritesActivity, SearchWatcherActivity
 * Responsibility: Enabling the bottom navigation bar in all activitys.
 */

 abstract class ActivityWithNavigation extends AppCompatActivity {
    private Activity activity;
    @Override
    public void onResume() {
        activity = this;
        super.onResume();
    }

    void initializeNavigationListener() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Class newActivityClass = null;


            switch (item.getItemId()) {
                case R.id.navigation_search:
                    newActivityClass = MainSearchActivity.class;
                    break;
                case R.id.navigation_favorites:
                    newActivityClass = FavoritesActivity.class;
                    break;
                case R.id.navigation_notifications:
                    newActivityClass = SearchWatcherActivity.class;
                    break;
                case R.id.navigation_settings:
                    newActivityClass = SettingsActivity.class;
                    break;
            }
            if (newActivityClass != null && !newActivityClass.equals(activity.getClass())) {
                ActivitySwitcher.getInstance(getApplicationContext()).navigate(newActivityClass);
                return true;
            }

            return false;
        }

    };
}
