package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.ActivitySwitcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.NameDialog;

public class AdvancedSearchActivityController implements Observer{
    private final Activity activity;
    private final AdvancedSearchFragmentController fragmentController;

    //While waiting for a name to become a SearchWatcher
    private Search wannabeSearchWatcher;


    public AdvancedSearchActivityController(Activity activity){
        this.activity = activity;
        initListeners();
        this.fragmentController = new AdvancedSearchFragmentController(activity.getWindow().getDecorView().getRootView());
    }

    private void initListeners() {
        ImageButton cancelButton = (ImageButton) activity.findViewById(R.id.cancel);
        Button advancedSearchButton = (Button) activity.findViewById(R.id.advancedSearchButton);
        Button createSearchWatcherButton = (Button) activity.findViewById(R.id.advancedSearchCreateSearchWatcherButton);

        cancelButton.setOnClickListener(onClickListener);
        advancedSearchButton.setOnClickListener(onAdvancedSearchListener);
        createSearchWatcherButton.setOnClickListener(onCreateSearchWatcherListener);
    }


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
            createNameDialog();
        }
    };

    private void createNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        NameDialog nameDialog = new NameDialog(builder, activity);
        new NameDialogController(builder, nameDialog, this);
    }

    private void returnToMainSearch(){
        activity.finish();
    }

    private void createSearchWatcher(String name){
        SearchWatcherItem sw = SearchWatcherModel.createSearchWatcher(name, wannabeSearchWatcher);
        Db4oDatabase.getInstance().store(sw);
        Db4oDatabase.getInstance().close();
    }

    //Called from NameDialog
    @Override
    public void update(String updateString) {
        createSearchWatcher(updateString);
    }
}
