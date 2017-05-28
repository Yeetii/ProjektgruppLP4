package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchList;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.ObjectActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.ActivitySwitcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AccommodationRecyclerViewHolder;

/**
 * Created by Jonathan on 16/04/2017.
 * @author John Segerstedt
 * Revised by Jonathan, Erik
 * John Segerstedt made the original ListvVew implementation
 * and Jonathan redid it as an RecyclerView
 * Used by: FavoritesActivity, FavoritesView, MainSearchActivity, RecyclerViewHelperController, SearchActivityController, SearchActivtyView
 * Uses: Search, SearchList, Accommodation, ImageModel, ObjectActivityModel, row_item
 * Responsibility: Adapter for the recyclerView that displays the accommodations
 */

public class AccommodationRecyclerViewAdapter extends RecyclerView.Adapter {

    private final List<Accommodation> dataSet;
    private final Class<? extends Activity> targetActivity;
    private View view;

    public AccommodationRecyclerViewAdapter(List<Accommodation> data, Class<? extends Activity> targetActivity) {
        dataSet = new ArrayList<>();
        dataSet.addAll(data);
        this.targetActivity = targetActivity;
        this.registerAdapterDataObserver();
    }

    private void registerAdapterDataObserver() {
        //Updates the object view to the last search, not optimal as it needs manual adjustment for example in favourite view
        ObjectActivityModel.setAccommodations(dataSet);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final AccommodationRecyclerViewHolder viewHolder = (AccommodationRecyclerViewHolder) holder;
        final Accommodation accommodation = dataSet.get(position);

        viewHolder.setCurrent(accommodation);
        viewHolder.setButton(viewHolder.isFavorite());
        viewHolder.whenBound();

        initButtonListener(viewHolder);
        initViewListener(viewHolder);
    }

    private void initViewListener(final AccommodationRecyclerViewHolder viewHolder) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                ObjectActivityModel.setStartPosition(viewHolder.getAdapterPosition());
                ActivitySwitcher.getInstance(v.getContext()).navigate(targetActivity);
            }
        });
    }

    private void initButtonListener(final AccommodationRecyclerViewHolder viewHolder) {
        ImageView favoriteButton = (ImageView) view.findViewById(R.id.favoriteButton);

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                viewHolder.getCurrent().setFavorite(!viewHolder.isFavorite());
                viewHolder.setButton(viewHolder.isFavorite());
            }
        });
    }

    public List<Accommodation> getAccommodations() {
        return dataSet;
    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

    @Override public AccommodationRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item,viewGroup, false);
        return new AccommodationRecyclerViewHolder(view);
    }

    public void refresh() {
        dataSet.clear();
        Search lastSearch = SearchList.getLastSearch();

        if (!lastSearch.isEmpty()) {
            dataSet.addAll(lastSearch.search());
        }else{
            dataSet.addAll(Accommodation.getAccommodations());
        }
        notifyDataSetChanged();
    }
}
