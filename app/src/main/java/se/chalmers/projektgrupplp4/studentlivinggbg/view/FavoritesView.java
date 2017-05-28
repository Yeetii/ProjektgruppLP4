package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.ObjectActivityModel;

/**
 * @author Jonathan
 * Revised by: Peter Gärdenäs, Erik Magnusson
 * Used by: FavoritesActivity
 * Uses: Accommodation, ObjectActivityModel
 * Responsibility: View for Favorites page
 */

public class FavoritesView {
    private final Activity activity;

    public FavoritesView(Activity activity) {
        this.activity = activity;
        activity.setContentView(R.layout.activity_favorites);
        //Updates the list of objects that are swipeable to
        ObjectActivityModel.setAccommodations(Accommodation.getFavorites());
        initLayoutManger();
        setSelectedNavigationItem();
    }

    private void initLayoutManger() {
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setSelectedNavigationItem () {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_favorites);
    }

}
