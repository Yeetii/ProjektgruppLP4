package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import se.chalmers.projektgrupplp4.studentlivinggbg.service.ActivitySwitcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.NameDialog;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;

public class AdvancedSearchActivityController implements Observer{
    private Activity activity;
    private AdvancedSearchFragmentController fragmentController;
    private Class<? extends Activity> targetClass;

    private Button advancedSearchButton;
    private Button createSearchWatcherButton;

    //While waiting for a name to become a SearchWatcher
    private Search wannabeSearchWatcher;


    public AdvancedSearchActivityController(Activity activity, Class<? extends Activity> targetClass){
        this.activity = activity;
        this.targetClass = targetClass;
        initListeners();
        this.fragmentController = new AdvancedSearchFragmentController(activity);
    }

    private void initListeners() {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        ImageButton cancelButton = (ImageButton) activity.findViewById(R.id.cancel);
        advancedSearchButton = (Button) activity.findViewById(R.id.advancedSearchButton);
        createSearchWatcherButton = (Button) activity.findViewById(R.id.advancedSearchCreateSearchWatcherButton);

        cancelButton.setOnClickListener(onClickListener);
        advancedSearchButton.setOnClickListener(onAdvancedSearchListener);
        createSearchWatcherButton.setOnClickListener(onCreateSearchWatcherListener);
    }
    
    private SearchView.OnClickListener onClickListenerSearch = new SearchView.OnClickListener() {
        @Override
        public void onClick(View view) {
            SearchView searchView = (SearchView) activity.findViewById(R.id.searchField);
            //switch (view.getId()) {
            //case R.id.searchField:
            searchView.onActionViewExpanded();
            //       break;
            //}
        }
    };

    private ImageButton.OnClickListener onClickListener = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            returnToMainSearch();
        }
    };

    private Button.OnClickListener onAdvancedSearchListener = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            fragmentController.parseSearchTerms(true);
            returnToMainSearch();
        }
    };

    private Button.OnClickListener onCreateSearchWatcherListener = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Saves the search and waits for nameDialog to finish
            wannabeSearchWatcher = fragmentController.parseSearchTerms(false);
//            createNameDialog();
            new NameDialog(activity, AdvancedSearchActivityController.this);
        }
    };

    private void returnToMainSearch(){
        ActivitySwitcher.getInstance(activity).navigate(targetClass);
    }

    private void createSearchWatcher(String name){
        System.out.println("Creating SW " + name);
        SearchWatcherModel.createSearchWatcher(name, wannabeSearchWatcher);
    }

    //Called from NameDialog
    @Override
    public void update(String updateString) {
        createSearchWatcher(updateString);
    }
}
