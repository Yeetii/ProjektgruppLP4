package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.database.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.NameDialog;
import se.chalmers.projektgrupplp4.studentlivinggbg.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.AdvancedSearchFragmentController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherView;

/**
 * Created by PG on 21/04/2017.
 */

public class SearchWatcherController implements Observer{
    private SearchWatcherView view;
    private Activity activity;
    private AdvancedSearchFragmentController fragment;
    private SearchWatcherAdapter adapter;


    public SearchWatcherController (SearchWatcherAdapter adapter, SearchWatcherView view, Activity activity) {
        this.adapter = adapter;
        this.view = view;
        this.activity = activity;

        fragment = new AdvancedSearchFragmentController(activity);

        initializeListeners();
        adapter.refresh();
    }

    private void initializeListeners() {
        initializeToggleModalListener();
        initializeDoNothingListener();
        initializeModalDoneButtonListener();
    }


    private void toggle() {
        System.out.println("Toggle");
        if (view.getModalVisibility()) {
            view.getFm().beginTransaction().hide(view.getFm().findFragmentById(R.id.searchWatcherModal)).commit();
        } else {
            view.getFm().beginTransaction().show(view.getFm().findFragmentById(R.id.searchWatcherModal)).commit();
        }
        view.toggleModalVisibility();
        updateModalButton();
    }

    //TODO This does not seem to work
    private void updateModalButton(){
        if(view.getModalVisibility()){
            view.getModalButton().setBackgroundResource(R.drawable.close_icon);
        }else{
            view.getModalButton().setBackgroundResource(R.drawable.plus_icon);
        }
    }

    private void initializeModalDoneButtonListener() {
        ImageButton modalDoneButton = (ImageButton) activity.findViewById(R.id.modalDoneButton);
        modalDoneButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                new NameDialog(activity, SearchWatcherController.this );
            }
        });
    }

    private void initializeDoNothingListener () {
        //TODO This is super bad code, remove before submit!
        ConstraintLayout searchWatcherContent = (ConstraintLayout) activity.findViewById(R.id.constraintLayout);

        searchWatcherContent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Don't do anything!");
            }
        });
    }

    private void initializeToggleModalListener () {
        ToggleButton toggleButton = (ToggleButton) activity.findViewById(R.id.modalButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toggle();
            }
        });

        ConstraintLayout searchWatcherBackground = (ConstraintLayout) activity.findViewById(R.id.backgroundModal);
        searchWatcherBackground.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.backgroundModal) toggle();
            }
        });
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
        toggle();
    }

    public boolean onBackPressed() {
        if (view.getModalVisibility()){
            toggle();
            return false;
        }else{
            return true;
        }
    }
}
