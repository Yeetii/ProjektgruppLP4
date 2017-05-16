package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import se.chalmers.projektgrupplp4.studentlivinggbg.NameDialog;
import se.chalmers.projektgrupplp4.studentlivinggbg.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.FavoritesActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.MainSearchActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;


public class AdvancedSearchActivityController implements Observer{
    private Activity activity;
    private AdvancedSearchFragmentController fragmentController;

    private Button advancedSearchButton;
    private Button createSearchWatcherButton;

    //While waiting for a name to become a SearchWatcher
    //TODO Should this be in the model?
    private Search wannabeSearchWatcher;


    public AdvancedSearchActivityController(Activity activity){
        this.activity = activity;
        initListeners();
        this.fragmentController = new AdvancedSearchFragmentController(activity);
    }

    private void initListeners() {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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

            //Är detta en bra lösning..?
           // SearchActivityModel.getInstance().refreshAdapter();

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
        Intent intent = new Intent(activity, MainSearchActivity.class);
        activity.startActivity(intent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    return true;
                case R.id.navigation_favorites:
                    Intent favorites = new Intent(activity, FavoritesActivity.class);
                    favorites.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    activity.startActivity(favorites);
                    return true;
                case R.id.navigation_notifications:
                    Intent searchWatcher = new Intent(activity, SearchWatcherActivity.class);
                    searchWatcher.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    activity.startActivity(searchWatcher);
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }

    };

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
