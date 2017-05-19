package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import se.chalmers.projektgrupplp4.studentlivinggbg.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.MainActivityHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SearchActivityView;

public class MainSearchActivity extends ActivityWithNavigation {
    private MainActivityHelper mainActivityHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityHelper = MainActivityHelper.getInstance(getApplicationContext());
        AccommodationRecyclerViewAdapter adapter = new AccommodationRecyclerViewAdapter(Accommodation.getAccommodations(), ObjectActivity.class);
        new SearchActivityView(this, adapter);
        new SearchActivityController(this, adapter,AdvancedSearchActivity.class);
        adapter.refresh();
        try {
            initializeNavigationListener();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        mainActivityHelper.saveDatabase();
        super.onDestroy();
    }



}
