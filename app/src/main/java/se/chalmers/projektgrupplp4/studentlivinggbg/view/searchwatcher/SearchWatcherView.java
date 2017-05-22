package se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher;

import android.app.Activity;
import android.widget.ListView;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.SearchWatcherAdapter;

/**
 * Created by PG on 21/04/2017.
 */

public class SearchWatcherView {
    private final Activity activity;

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
