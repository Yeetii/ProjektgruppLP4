package se.chalmers.projektgrupplp4.studentlivinggbg.view.SearchWatcher;

import android.app.Activity;
import android.app.FragmentManager;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by PG on 21/04/2017.
 */

public class SearchWatcherView {
    private Activity activity;
    private FragmentManager fm;
    private boolean isModalVisible = false;

    public SearchWatcherView (Activity activity) {
        this.activity = activity;
        fm = activity.getFragmentManager();
        initializeView();
    }

    private void initializeView () {
        activity.setContentView(R.layout.search_watcher);
        activity.setTitle(R.string.title_notifications);
    }

    public void toggleModalVisibility() {
        System.out.println("Toggle");
        if (isModalVisible) {
            fm.beginTransaction().hide(fm.findFragmentById(R.id.searchWatcherModal)).commit();
        } else {
            fm.beginTransaction().show(fm.findFragmentById(R.id.searchWatcherModal)).commit();
        }
        isModalVisible = !isModalVisible;
    }

    public void hideModal () {
        fm.beginTransaction().hide(fm.findFragmentById(R.id.searchWatcherModal)).commit();
        isModalVisible = false;
    }
}
