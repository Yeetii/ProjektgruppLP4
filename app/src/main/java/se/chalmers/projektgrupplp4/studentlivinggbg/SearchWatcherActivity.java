package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import se.chalmers.projektgrupplp4.studentlivinggbg.Controller.SearchWatcherController;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.SearchWatcherViewModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.View.SearchWatcherView;

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