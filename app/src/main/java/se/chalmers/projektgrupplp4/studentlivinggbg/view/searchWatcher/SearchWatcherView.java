package se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher;

import android.app.Activity;
import android.app.FragmentManager;
import android.widget.ListView;
import android.widget.ToggleButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.SearchWatcherAdapter;

/**
 * Created by PG on 21/04/2017.
 */

public class SearchWatcherView {
    private Activity activity;
    private SearchWatcherAdapter adapter;

    public SearchWatcherView (Activity activity, SearchWatcherAdapter adapter) {
        this.activity = activity;
        initializeView();

        ListView listView = (ListView) activity.findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    private void initializeView () {
        activity.setContentView(R.layout.search_watcher);
        activity.setTitle(R.string.title_notifications);
    }
}
