package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;

/**
 * Created by Jonathan on 16/04/2017.
 */

public class AccommodationRecyclerViewAdapter extends RecyclerView.Adapter implements RecyclerViewHolderObserver {

    private List<Accommodation> dataSet;
    private Context mContext;

    public AccommodationRecyclerViewAdapter(List<Accommodation> data, Context context) {
        dataSet = new ArrayList<>();
        for (Accommodation i : data) {
            dataSet.add(i);
        }
        this.mContext=context;
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
        viewHolder.image.setImageDrawable(accommodation.getImage());
        viewHolder.current = accommodation;
        viewHolder.position = position;
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
}
