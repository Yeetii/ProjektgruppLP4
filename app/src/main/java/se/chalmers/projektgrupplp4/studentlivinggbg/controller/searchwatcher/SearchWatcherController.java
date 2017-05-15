package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.ToggleButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.BottomNavigationListener;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherViewModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher.SearchWatcherView;

/**
 * Created by PG on 21/04/2017.
 */

public class SearchWatcherController {
    private SearchWatcherViewModel model;
    private SearchWatcherView view;
    private Activity activity;


    public SearchWatcherController (SearchWatcherViewModel model, SearchWatcherView view) {
        this.model = model;
        this.view = view;
        this.activity = model.getActivity();
        initializeListeners();
    }

    private void initializeListeners() {
        initializeNavigationListener();
        initializeToggleModalListener();
        initializeDoNothingListener();
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

    private void initializeDoNothingListener () {
        //This is super bad code, remove before submit!
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


    private void initializeNavigationListener () {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(BottomNavigationListener.getInstance());
        navigation.setSelectedItemId(R.id.navigation_notifications);
    }
}
