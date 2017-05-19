package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import se.chalmers.projektgrupplp4.studentlivinggbg.view.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.MainActivityHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SearchActivityView;

public class MainSearchActivity extends AppCompatActivity {
    private MainActivityHelper mainActivityHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityHelper = MainActivityHelper.getInstance(getApplicationContext());
        AccommodationRecyclerViewAdapter adapter = new AccommodationRecyclerViewAdapter(Accommodation.getAccommodations());
        SearchActivityView searchActivityView = new SearchActivityView(this, adapter);
        new SearchActivityController(this, searchActivityView, adapter);
        adapter.refresh();
    }

    @Override
    protected void onDestroy() {
        mainActivityHelper.saveDatabase();
        super.onDestroy();
    }



}
