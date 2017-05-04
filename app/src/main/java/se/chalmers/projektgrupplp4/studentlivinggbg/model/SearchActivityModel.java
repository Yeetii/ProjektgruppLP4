package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;

import se.chalmers.projektgrupplp4.studentlivinggbg.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by PG on 29/04/2017.
 */

public class SearchActivityModel {
    private AccommodationRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private ImageView favoriteButton;


    public SearchActivityModel (Activity activity) {
        recyclerViewAdapter = new AccommodationRecyclerViewAdapter(MainModel.getInstance().getAccommodations(), activity.getApplicationContext());
        recyclerView = (RecyclerView) activity.findViewById(R.id.list);

        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void changeFavorite(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();

        if (direction == ItemTouchHelper.LEFT){
            recyclerViewAdapter.removeFromFavorites(position);
        } else { //right?
            recyclerViewAdapter.addToFavorites(position);
        }
        recyclerViewAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
    }

    public void refreshAdapter() {
        recyclerViewAdapter.clear();
        recyclerViewAdapter.addAll(MainModel.getInstance().getAccommodations());
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

}
