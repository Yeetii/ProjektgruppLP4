package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.AdvancedSearchFragmentController;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.ActivitySwitcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.ModalView;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherItemView;

/**
 * Created by PG on 23/04/2017.
 */

public class SearchWatcherItemController implements Observer {
    private SearchWatcherItem model;
    private View view;
    private Class<? extends  Activity> targetClass;
    private Activity activity;
    private ModalController modalController;
    private ModalView modalView;
    private AdvancedSearchFragmentController fragment;


    private ConstraintLayout layout;

    public SearchWatcherItemController (SearchWatcherItemView view, Activity activity, Class<? extends  Activity> targetClass) {
        this.model = view.getModel();
        this.view = view.getView();
        this.activity = activity;
        //Not the best solution perhaps as new controllers and views are created for the same modal, would singleton be better?
        //Couldn't pass it through constructors
//        this.modalView = new ModalView(activity, model);
//        this.modalController = new ModalController(activity, modalView, this);
        this.targetClass = targetClass;
        addListeners();

    }

    private void addListeners() {
        ImageView hamButton = (ImageView) view.findViewById(R.id.hamButton);
        hamButton.setOnClickListener(getEditSearchWatcherListener());

        ImageView searchButton = (ImageView) view.findViewById(R.id.searchWithSearchWatcherButton);
        searchButton.setOnClickListener(getSearchSearchWatcherListener());

        view.setOnClickListener(toggleExpanded());
    }

    private View.OnClickListener toggleExpanded() {
        return (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Make the code more efficient.

                if (model.isExpanded()) {
                    model.resetExpanded();
                } else {
                    model.setExpanded();
                }

                switch (v.getId())
                {
                    case R.id.searchWithSearchWatcherButton:
                        //TODO not used atm
//                AdvancedSearchActivityController.advancedSearchButtonPressed(v);
                        break;
                }
            }
        });
    }

    private ImageView.OnClickListener getEditSearchWatcherListener() {
        return (new ImageView.OnClickListener () {
            @Override
            public void onClick (View view) {
                modalController.close();
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
                ActivitySwitcher.getInstance(activity).navigate(targetClass);
            }
        });
    }

    //Called by Modal
    @Override
    public void update(String updateString) {

    }
}
