package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;

import se.chalmers.projektgrupplp4.studentlivinggbg.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherModalFragment;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.ActivitySwitcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by PG on 23/04/2017.
 */

public class SearchWatcherItemController {
    private final SearchWatcherItem model;
    private final View view;
    private final Class<? extends  Activity> targetClass;
    private final Activity activity;
    private final SearchWatcherAdapter adapter;

    public SearchWatcherItemController (SearchWatcherItem model, View view, Activity activity, SearchWatcherAdapter adapter, Class<? extends  Activity> targetClass) {
        this.model = model;
        this.view = view;
        this.activity = activity;
        this.adapter = adapter;
        this.targetClass = targetClass;
        addListeners();

    }

    private void addListeners() {
        ImageView hamButton = (ImageView) view.findViewById(R.id.hamButton);
        hamButton.setOnClickListener(getEditSearchWatcherListener());

        ImageView searchButton = (ImageView) view.findViewById(R.id.searchWithSearchWatcherButton);
        searchButton.setOnClickListener(getSearchSearchWatcherListener());

        ConstraintLayout layout = (ConstraintLayout) view.findViewById(R.id.row_item);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private ImageView.OnClickListener getEditSearchWatcherListener() {
        return (new ImageView.OnClickListener () {
            @Override
            public void onClick (View view) {
                SearchWatcherModalFragment.newSearchWatcherModalFragment(activity, adapter, R.id.searchWatcherView, model);
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
}
