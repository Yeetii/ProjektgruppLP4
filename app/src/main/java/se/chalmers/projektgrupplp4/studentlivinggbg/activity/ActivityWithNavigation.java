package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import se.chalmers.projektgrupplp4.studentlivinggbg.service.ActivitySwitcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by PG on 18/05/2017.
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

    protected BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
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
            if (!newActivityClass.equals(activity.getClass())) {
                ActivitySwitcher.getInstance(getApplicationContext()).navigate(newActivityClass);
                return true;
            }

            return false;
        }

    };
}
