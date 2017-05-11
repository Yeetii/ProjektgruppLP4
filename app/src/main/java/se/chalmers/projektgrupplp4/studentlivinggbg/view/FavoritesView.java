package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.ObjectActivityModel;

/**
 * Created by PG on 07/05/2017.
 */

public class FavoritesView {
    private RecyclerView recyclerView;
    private Activity activity;

    public FavoritesView(Activity activity) {
        this.activity = activity;
        activity.setContentView(R.layout.activity_favorites);
        //Updates the list of objects that are swipeable to
        ObjectActivityModel.setAccommodations(Accommodation.getFavorites());
        initLayoutManger();
    }

    private void initLayoutManger() {
        recyclerView = (RecyclerView) activity.findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
