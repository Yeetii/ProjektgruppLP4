package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.BottomNavigationListener;
import se.chalmers.projektgrupplp4.studentlivinggbg.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.NameDialog;
import se.chalmers.projektgrupplp4.studentlivinggbg.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.AdvancedSearchFragmentController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher.ModalView;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher.SearchWatcherView;

/**
 * Created by PG on 21/04/2017.
 */

public class SearchWatcherController implements Observer{
    private SearchWatcherView view;
    private Activity activity;
    private AdvancedSearchFragmentController fragment;
    private SearchWatcherAdapter adapter;
    private ModalController modalController;
    private ModalView modalView;


    public SearchWatcherController (SearchWatcherAdapter adapter, SearchWatcherView view, Activity activity) {
        this.adapter = adapter;
        this.view = view;
        this.activity = activity;

        fragment = new AdvancedSearchFragmentController(activity);
        modalView = new ModalView(activity);
        modalController = new ModalController(activity, modalView, this);

        initializeListeners();
        adapter.refresh();
    }

    private void initializeListeners() {
        initializeNavigationListener();
    }



    private void initializeNavigationListener () {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(BottomNavigationListener.getInstance());
        navigation.setSelectedItemId(R.id.navigation_notifications);
    }

    private void createSearchWatcher(String name){
        System.out.println("Creating SW " + name);
        Search search = fragment.parseSearchTerms(false);
        SearchWatcherItem searchWatcher = SearchWatcherModel.createSearchWatcher(name, search);
        Db4oDatabase.getInstance().store(searchWatcher);
        adapter.refresh();
    }

    //Called from NameDialog
    @Override
    public void update(String updateString) {
        createSearchWatcher(updateString);
        modalController.toggle();
    }

    public boolean onBackPressed() {
        if (modalView.getModalVisibility()){
            modalController.toggle();
            return false;
        }else{
            return true;
        }
    }

    public void onStart () {
        modalView.hideModal();
    }
}
