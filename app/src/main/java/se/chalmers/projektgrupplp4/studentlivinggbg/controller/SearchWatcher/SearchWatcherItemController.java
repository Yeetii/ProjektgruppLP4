package se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchWatcher;

import android.view.View;
import android.widget.ImageView;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchWatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by PG on 23/04/2017.
 */

public class SearchWatcherItemController {
    private SearchWatcherItem model;
    private View view;

    public SearchWatcherItemController (SearchWatcherItem model, View view) {
        this.model = model;
        this.view = view;
        addEditSearchWatcherListener();
    }

    private void addEditSearchWatcherListener () {
        ImageView imageView = (ImageView) view.findViewById(R.id.hamButton);
        imageView.setOnClickListener(getEditSearchWatcherListener());
    }

    private ImageView.OnClickListener getEditSearchWatcherListener() {
        return (new ImageView.OnClickListener () {
            @Override
            public void onClick (View view) {
                model.editSearchWatcher();
            }
        });
    }
}
