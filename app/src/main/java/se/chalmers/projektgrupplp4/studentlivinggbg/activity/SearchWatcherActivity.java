package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import se.chalmers.projektgrupplp4.studentlivinggbg.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher.SearchWatcherController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AdvancedSearchFragmentView;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher.SearchWatcherView;

/**
 * Created by PG on 03/04/2017.
 */

public class SearchWatcherActivity extends AppCompatActivity {
    private SearchWatcherView view;
    private SearchWatcherController controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchWatcherAdapter adapter = new SearchWatcherAdapter(getApplicationContext(), new ArrayList<SearchWatcherItem>(), this);

        this.view = new SearchWatcherView(this, adapter);
        controller = new SearchWatcherController(adapter, view, this);

        new AdvancedSearchFragmentView(this);
    }

    @Override
    public void onStart () {
        super.onStart();
        controller.onStart();
    }

    @Override
    public void onBackPressed(){
        if (controller.onBackPressed())
            super.onBackPressed();
    }
}