package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.imagemodel.ImageModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.ObjectActivityModel;

/**
 * Created by Jonathan on 16/04/2017.
 */

public class AccommodationRecyclerViewAdapter extends RecyclerView.Adapter implements RecyclerViewHolderObserver {

    private List<Accommodation> dataSet;

    public AccommodationRecyclerViewAdapter(List<Accommodation> data) {
        dataSet = new ArrayList<>();
        for (Accommodation i : data) {
            dataSet.add(i);
        }
        this.registerAdapterDataObserver(this);
    }

    private void registerAdapterDataObserver(AccommodationRecyclerViewAdapter accommodationRecyclerViewAdapter) {
        //Updates the object view to the last search, not optimal as it needs manual adjustment for example in favourite view
        ObjectActivityModel.setAccommodations(dataSet);
    }

    public void clear () {
        dataSet.clear();
    }

    public void addAll (List<Accommodation> dataSet) {
        for (Accommodation i : dataSet) {
            this.dataSet.add(i);
        }
        notifyDataSetChanged();
    }

    public void setFavorite (int position, boolean value) {
        Accommodation.getAccommodations().get(Accommodation.getAccommodations().indexOf(this.dataSet.get(position))).setFavorite(value);
    }


    private void toggleFavoriteStatus(AccommodationRecyclerViewHolder viewHolder) {
        int drawable = viewHolder.isFavorite() ? R.drawable.favorite_off : R.drawable.favorite_on;
        viewHolder.favoriteButton.setImageResource(drawable);
        viewHolder.getCurrent().setFavorite(!viewHolder.isFavorite());
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AccommodationRecyclerViewHolder viewHolder = (AccommodationRecyclerViewHolder) holder;
        final Accommodation accommodation = dataSet.get(position);

        viewHolder.add(this);
        viewHolder.txtAddress.setText(accommodation.getAddress());
        viewHolder.txtHouseType.setText(accommodation.getAccommodationHouseType());
        viewHolder.txtArea.setText(accommodation.getArea());
        viewHolder.txtPrice.setText(accommodation.getPrice());
        viewHolder.txtLastApplyDate.setText(accommodation.getLastApplyDate());
        if(accommodation.getFavorite()) {
            viewHolder.favoriteButton.setImageResource(R.drawable.favorite_on);
        } else {
            viewHolder.favoriteButton.setImageResource(R.drawable.favorite_off);
        }
        viewHolder.image.setImageDrawable(ImageModel.<Drawable>getInstance().getMainImage(accommodation.getImagePath()));
        viewHolder.current = accommodation;
        viewHolder.position = position;
    }

    public List<Accommodation> getAccommodations() {
        return dataSet;
    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

    @Override public AccommodationRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item,viewGroup, false);
        return new AccommodationRecyclerViewHolder(view);
    }


    @Override
    public void update(AccommodationRecyclerViewHolder viewHolder) {
        toggleFavoriteStatus(viewHolder);
    }

    public void refresh() {
        dataSet.clear();
        Search lastSearch = SearchHandler.getLastSearch();

        if (!lastSearch.isEmpty()) {
            dataSet.addAll(lastSearch.search());
        }else{
            dataSet.addAll(Accommodation.getAccommodations());
        }
        notifyDataSetChanged();
    }
}
