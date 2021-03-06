package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * @author Peter Gärdenäs
 * Used by: MainSearchActivity
 * Uses: activity_main_search
 * Responsibility: View for the search/home page
 */

public class SearchActivityView {
    private final Activity activity;
    private final RecyclerView recyclerView;

    public SearchActivityView (Activity activity) {
        this.activity = activity;
        activity.setContentView(R.layout.activity_main_search);
        this.recyclerView = (RecyclerView) activity.findViewById(R.id.list);
        initLayoutManager();
    }

    private void initLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
