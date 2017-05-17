package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import se.chalmers.projektgrupplp4.studentlivinggbg.NavigationHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.MainSearchActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by PG on 23/04/2017.
 */

public class SearchWatcherItemController {
    private SearchWatcherItem model;
    private View view;
    private Activity activity;

    public SearchWatcherItemController (SearchWatcherItem model, View view, Activity activity) {
        this.model = model;
        this.view = view;
        this.activity = activity;
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
}
