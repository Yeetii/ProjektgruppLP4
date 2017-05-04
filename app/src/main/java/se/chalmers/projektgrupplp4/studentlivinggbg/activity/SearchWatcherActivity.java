package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher.SearchWatcherController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherViewModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher.SearchWatcherView;

/**
 * Created by PG on 03/04/2017.
 */

public class SearchWatcherActivity extends AppCompatActivity {
    private SearchWatcherView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.view = new SearchWatcherView(this);
        SearchWatcherViewModel model = new SearchWatcherViewModel(this);
        new SearchWatcherController(model, view);
    }

    //Should this be done in the controller?
    @Override
    public void onStart () {
        super.onStart();
        view.hideModal();
    }
}