package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.ImageButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.fragment.SearchWatcherModalFragment;

/**
 * Created by PG on 21/04/2017.
 */

public class SearchWatcherController{
    private Activity activity;
    private SearchWatcherAdapter adapter;
    private FragmentManager fragmentManager;

    public SearchWatcherController(SearchWatcherAdapter adapter, Activity activity) {
        this.adapter = adapter;
        this.activity = activity;

        initializeListeners();
    }

    private void initializeListeners() {
        initializeNavigationListener();
        initializeNewSWListener();
//        initializeDoNothingListener();
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
                fragmentManager = activity.getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SearchWatcherModalFragment fragment = new SearchWatcherModalFragment();
                fragment.setAdapter(adapter);
                fragmentTransaction.add(R.id.searchWatcherView, fragment);
                fragmentTransaction.addToBackStack("tag").commit();
            }
        });
    }
}
