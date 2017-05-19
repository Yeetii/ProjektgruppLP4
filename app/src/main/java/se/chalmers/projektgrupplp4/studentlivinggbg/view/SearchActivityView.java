package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by PG on 28/04/2017.
 */

public class SearchActivityView {
    private Activity activity;
    private RecyclerView recyclerView;

    public SearchActivityView (Activity activity, AccommodationRecyclerViewAdapter adapter) {
        this.activity = activity;
        activity.setContentView(R.layout.activity_main_search);
        this.recyclerView = (RecyclerView) activity.findViewById(R.id.list);
        initLayoutManager(adapter);
        recyclerView.setAdapter(adapter);
    }

    private void initLayoutManager(AccommodationRecyclerViewAdapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
