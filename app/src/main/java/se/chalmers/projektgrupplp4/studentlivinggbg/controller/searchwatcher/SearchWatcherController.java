package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Activity;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.ImageButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherModalFragment;

/**
 * Created by PG on 21/04/2017.
 */

public class SearchWatcherController{
    private final Activity activity;
    private final SearchWatcherAdapter adapter;

    public SearchWatcherController(SearchWatcherAdapter adapter, Activity activity) {
        this.adapter = adapter;
        this.activity = activity;

        initializeListeners();
    }

    private void initializeListeners() {
        initializeNavigationListener();
        initializeNewSWListener();
    }

    private void initializeNavigationListener () {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        //TODO SOMETHING
//        navigation.setOnNavigationItemSelectedListener(BottomNavigationListener.getInstance());
        navigation.setSelectedItemId(R.id.navigation_notifications);
    }

    private void initializeNewSWListener(){
        ImageButton newSWButton = (ImageButton) activity.findViewById(R.id.newSearchWatcherButton);
        newSWButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchWatcherModalFragment.newSearchWatcherModalFragment(activity, adapter, R.id.searchWatcherView);
            }
        });
    }
}
