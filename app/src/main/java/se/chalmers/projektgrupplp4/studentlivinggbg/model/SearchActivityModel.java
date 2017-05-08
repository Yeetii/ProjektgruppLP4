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
    private Search lastSearch;


    public SearchActivityModel (Activity activity) {
        recyclerViewAdapter = new AccommodationRecyclerViewAdapter(Accommodation.getAccommodations(), activity.getApplicationContext());
        recyclerView = (RecyclerView) activity.findViewById(R.id.list);

        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void setLastSearch(Search lastSearch) {
        this.lastSearch = lastSearch;
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

        lastSearch = SearchHandler.getLastSearch();

        if (isNotNull(lastSearch)) {
            recyclerViewAdapter.addAll(SearchHandler.search(lastSearch));
        }else{
            recyclerViewAdapter.addAll(Accommodation.getAccommodations());
        }
        recyclerViewAdapter.notifyDataSetChanged();
    }

    public void refreshNotUpdateAdapter() {
        recyclerViewAdapter.clear();
        recyclerViewAdapter.addAll(Accommodation.getAccommodations());
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private boolean isNotNull(Search lastSearch) {

        try {
            return lastSearch != null && (!lastSearch.getMainSearch().equals("") ||
                    !lastSearch.getAddress().equals("") ||
                    !lastSearch.getPossibleAccommodationHosts().isEmpty() ||
                    !lastSearch.getPossibleAccomodationHouseTypes().isEmpty() ||
                    !(lastSearch.getMaxArea() == -1) ||
                    !(lastSearch.getMinArea() == -1) ||
                    !(lastSearch.getMaxPrice() == -1) ||
                    !(lastSearch.getMinPrice() == -1) ||
                    !(lastSearch.getMaxArea() == -1) ||
                    !(lastSearch.getMaxSearchers() == -1) ||
                    !lastSearch.getLastApplyDate().equals("") ||
                    !lastSearch.getUpploadDate().equals(""));
        }
        catch(NullPointerException e){
            return false;
        }
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

}
