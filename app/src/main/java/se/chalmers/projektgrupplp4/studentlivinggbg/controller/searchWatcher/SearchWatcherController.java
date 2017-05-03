package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchWatcher;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.FavoritesActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.MainSearchActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchWatcher.SearchWatcherViewModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher.SearchWatcherView;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

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
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_search:
                        Intent search = new Intent(activity, MainSearchActivity.class);
                        search.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        activity.startActivity(search);
                        return true;
                    case R.id.navigation_favorites:
                        Intent favorites = new Intent(activity, FavoritesActivity.class);
                        favorites.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        activity.startActivity(favorites);
                        return true;
                    case R.id.navigation_notifications:
                        return true;
                    case R.id.navigation_settings:
                        return true;
                }
                return false;
            }

        };

        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_notifications);
    }
}
