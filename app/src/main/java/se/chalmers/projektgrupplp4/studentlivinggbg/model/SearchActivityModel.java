package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import se.chalmers.projektgrupplp4.studentlivinggbg.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;

/**
 * Created by PG on 29/04/2017.
 */

public class SearchActivityModel {

    static SearchActivityModel searchActivityModel;
    static int numberOfInstances = 0;

    static private AccommodationRecyclerViewAdapter lastRecyclerViewAdapter;
    private AccommodationRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private Search lastSearch;

    private SearchActivityModel (Activity activity) {
        if (lastRecyclerViewAdapter == null) {
            recyclerViewAdapter = new AccommodationRecyclerViewAdapter(Accommodation.getAccommodations(), activity.getApplicationContext());
        }else {
            recyclerViewAdapter = new AccommodationRecyclerViewAdapter(lastRecyclerViewAdapter.getAccommodations(), activity.getApplicationContext());
        }
        lastRecyclerViewAdapter = recyclerViewAdapter;
        recyclerView = (RecyclerView) activity.findViewById(R.id.list);
        recyclerView.setAdapter(recyclerViewAdapter);
        numberOfInstances++;
    }

    static public SearchActivityModel createInstance(Activity activity){
        //if(searchActivityModel == null){
        searchActivityModel = new SearchActivityModel(activity);
    //}
        return searchActivityModel;
    }

    static public SearchActivityModel getInstance(){
        return searchActivityModel;
    }

    static public SearchActivityModel getInstance(Activity activity){
        if(numberOfInstances > 0){
            return searchActivityModel;}
        else{
            searchActivityModel = new SearchActivityModel(activity);
            return searchActivityModel;
        }
    }

    public void setLastSearch(Search lastSearch) {
        this.lastSearch = lastSearch;
    }

    public void changeFavorite(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();

        recyclerViewAdapter.setFavorite(position, direction != ItemTouchHelper.LEFT);
        recyclerViewAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
    }

    public void refreshAdapter() {
        recyclerViewAdapter.clear();

        lastSearch = SearchHandler.getLastSearch();


        //TODO THE FOLLOWING CODE HAS STOPPED WORKING!!!
        if (!lastSearch.isEmpty()) {
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

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public AccommodationRecyclerViewAdapter getRecyclerViewAdapter() {
        return recyclerViewAdapter;
    }
}
