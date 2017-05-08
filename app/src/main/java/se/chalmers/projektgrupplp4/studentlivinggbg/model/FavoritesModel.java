package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import android.app.Activity;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.RecyclerView;

import se.chalmers.projektgrupplp4.studentlivinggbg.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by PG on 07/05/2017.
 */

public class FavoritesModel {
    private AccommodationRecyclerViewAdapter recyclerViewAdapter;
    private Activity activity;

    public FavoritesModel(Activity activity) {
        this.activity = activity;
        initRecycleAdapter();
        setSelectedNavigationItem();
    }

    private void initRecycleAdapter() {
        recyclerViewAdapter = new AccommodationRecyclerViewAdapter(Accommodation.getFavorites(), activity.getApplicationContext());
        ((RecyclerView) activity.findViewById(R.id.list)).setAdapter(recyclerViewAdapter);
    }

    private void setSelectedNavigationItem () {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_favorites);
    }

    public void updateFavoriteStatus(int position, boolean favorite, RecyclerView.ViewHolder viewHolder) {
        if (favorite) {
            recyclerViewAdapter.addToFavorites(position);
        } else {
            recyclerViewAdapter.removeFromFavorites(position);
        }
        recyclerViewAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
    }
}
