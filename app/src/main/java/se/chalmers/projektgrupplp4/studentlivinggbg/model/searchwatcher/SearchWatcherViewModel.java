package se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher;

import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.SearchWatcherAdapter;

/**
 * Created by PG on 21/04/2017.
 */

//Stupid name, any good suggestions?
public class SearchWatcherViewModel {
    private SearchWatcherAdapter adapter;
    private Activity activity;

    public SearchWatcherViewModel (Activity activity) {
        this.activity = activity;
        adapter = new SearchWatcherAdapter(activity.getApplicationContext(), new ArrayList<SearchWatcherItem>(), activity);
        ListView listView = (ListView) activity.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        refreshAdapter();
    }

    public void refreshAdapter(){
        adapter.clear();
        adapter.addAll(SearchWatcherModel.getSearchWatcherItems());
    }

    public Activity getActivity () {
        return activity;
    }

}
