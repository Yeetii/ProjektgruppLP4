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
    private Activity activity;
    private SearchWatcherAdapter adapter;
    private ModalController modalController;
    private ModalView modalView;


    public SearchWatcherController(SearchWatcherAdapter adapter, Activity activity) {
        this.adapter = adapter;
        this.activity = activity;

        this.modalView = new ModalView(activity, true);
        this.modalController = new ModalController(activity, modalView, this);

        initializeListeners();
        adapter.refresh();
    }

    private void initializeListeners() {
        initializeNavigationListener();
        initializeNewSWListener();
    }

    private void initializeNavigationListener () {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(BottomNavigationListener.getInstance());
        navigation.setSelectedItemId(R.id.navigation_notifications);
    }

    private void initializeNewSWListener(){
        ImageButton newSWButton = (ImageButton) activity.findViewById(R.id.newSearchWatcherButton);
        newSWButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modalController.toggle();
            }
        });
    }

    private void createSearchWatcher(String name){
        System.out.println("Creating SW " + name);
        Search search = modalController.parseSearchTerms(false);
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
