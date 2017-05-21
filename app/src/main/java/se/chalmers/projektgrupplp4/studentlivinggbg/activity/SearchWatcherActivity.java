package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import se.chalmers.projektgrupplp4.studentlivinggbg.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher.ModalController;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher.SearchWatcherController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AdvancedSearchFragmentView;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher.ModalView;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher.SearchWatcherView;

/**
 * Created by PG on 03/04/2017.
 */

public class SearchWatcherActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchWatcherAdapter adapter = new SearchWatcherAdapter(getApplicationContext(), SearchWatcherModel.getSearchWatcherItems(), this);

        new SearchWatcherView(this, adapter);
        new SearchWatcherController(adapter, this);
    }
}