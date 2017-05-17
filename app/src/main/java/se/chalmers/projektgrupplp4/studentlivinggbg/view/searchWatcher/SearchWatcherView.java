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
    private ToggleButton modalButton;
    private FragmentManager fm;
    private boolean modalVisibility = false;
    private SearchWatcherAdapter adapter;

    public SearchWatcherView (Activity activity, SearchWatcherAdapter adapter) {
        this.activity = activity;
        this.modalButton = new ToggleButton(activity);
        this.fm = activity.getFragmentManager();
        initializeView();

        ListView listView = (ListView) activity.findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    private void initializeView () {
        activity.setContentView(R.layout.search_watcher);
        activity.setTitle(R.string.title_notifications);
    }



    public void hideModal () {
        fm.beginTransaction().hide(fm.findFragmentById(R.id.searchWatcherModal)).commit();
        modalVisibility = false;
    }

    public ToggleButton getModalButton() {
        return modalButton;
    }

    public FragmentManager getFm() {
        return fm;
    }

    public boolean getModalVisibility() {
        return modalVisibility;
    }

    public void toggleModalVisibility(){
        modalVisibility = !modalVisibility;
    }
}
