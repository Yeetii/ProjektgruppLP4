package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import se.chalmers.projektgrupplp4.studentlivinggbg.NavigationHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.MainSearchActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.AdvancedSearchFragmentController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher.ModalView;

/**
 * Created by PG on 23/04/2017.
 */

public class SearchWatcherItemController implements Observer{
    private SearchWatcherItem model;
    private View view;
    private Activity activity;
    private ModalController modalController;
    private ModalView modalView;
    private AdvancedSearchFragmentController fragment;


    public SearchWatcherItemController (SearchWatcherItem model, View view, Activity activity) {
        this.model = model;
        this.view = view;
        this.activity = activity;
        //Not the best solution perhaps as new controllers and views are created for the same modal, would singleton be better?
        //Couldn't pass it through constructors
        this.modalView = new ModalView(activity, model);
        this.modalController = new ModalController(activity, modalView, this);
        addListeners();
    }

    private void addListeners() {
        ImageView hamButton = (ImageView) view.findViewById(R.id.hamButton);
        hamButton.setOnClickListener(getEditSearchWatcherListener());

        ImageView searchButton = (ImageView) view.findViewById(R.id.searchWithSearchWatcherButton);
        searchButton.setOnClickListener(getSearchSearchWatcherListener());
    }

    private ImageView.OnClickListener getEditSearchWatcherListener() {
        return (new ImageView.OnClickListener () {
            @Override
            public void onClick (View view) {
                modalController.toggle();
                model.editSearchWatcher();
            }
        });
    }

    private ImageView.OnClickListener getSearchSearchWatcherListener() {
        return (new ImageView.OnClickListener () {
            @Override
            public void onClick (View view) {
                Search search = model.getSearch();
                SearchHandler.addToLastSearches(search);
                NavigationHelper.getInstance(activity).navigateToMainActivity();
            }
        });
    }

    //Called by Modal
    @Override
    public void update(String updateString) {

    }
}
