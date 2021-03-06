package se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher;

import android.app.Activity;
import android.widget.ListView;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * @author Peter Gärdenäs
 * Used by: SearchWatcherActivity
 * Uses: search_watcher
 * Responsibility: View for SearchWatcher page
 */

public class SearchWatcherView {
    private final Activity activity;

    public SearchWatcherView (Activity activity) {
        this.activity = activity;
        initializeView();
    }

    public void setAdapter (SearchWatcherAdapter adapter) {
        ListView listView = (ListView) activity.findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    private void initializeView () {
        activity.setContentView(R.layout.search_watcher);
        activity.setTitle(R.string.title_notifications);
    }
}
