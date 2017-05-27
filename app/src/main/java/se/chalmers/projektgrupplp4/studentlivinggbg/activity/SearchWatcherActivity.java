package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher.SearchWatcherController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherView;

/**
 * @author Erik Magnusson
 * Revised by: Peter Gärdenäs
 * Used by: ActivityWithNavigation, ActivityReceiver.
 * Uses: SearchWatcherController, SearchWatcherModel, SearchWatcherAdapter, SearchWatcherView, MainSearchActivity
 * Responsibility: Creating the search watcher activity.
 */


public class SearchWatcherActivity extends ActivityWithNavigation {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchWatcherView view = new SearchWatcherView(this);

        SearchWatcherController controller = new SearchWatcherController(this, MainSearchActivity.class);
        SearchWatcherAdapter adapter = new SearchWatcherAdapter(getApplicationContext(), SearchWatcherModel.getSearchWatcherItems(), controller);
        controller.setAdapter(adapter);
        view.setAdapter(adapter);
        controller.update(null);


        initializeNavigationListener();
    }
}