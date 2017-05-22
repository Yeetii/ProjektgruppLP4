package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher.SearchWatcherController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherView;

/**
 * Created by PG on 03/04/2017.
 */

public class SearchWatcherActivity extends ActivityWithNavigation {
    private SearchWatcherView view;
    private SearchWatcherController controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchWatcherController controller = new SearchWatcherController(this, MainSearchActivity.class);
        SearchWatcherAdapter adapter = new SearchWatcherAdapter(getApplicationContext(), SearchWatcherModel.getSearchWatcherItems(), controller);
        controller.setAdapter(adapter);
        controller.update(null);

        new SearchWatcherView(this, adapter);

        initializeNavigationListener();
    }
}