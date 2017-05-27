package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.ObjectActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.ActivitySwitcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;

/**
 * Created by Jonathan on 18/04/2017.
 * @author John
 * revised by Jonathan
 * John made the original ListvVew implementation
 * and Jonathan redid it as an RecyclerView
 */

public class AccommodationRecyclerViewHolder extends RecyclerView.ViewHolder implements RecyclerViewHolderObservable {
        final TextView txtAddress;
        final TextView txtHouseType;
        final TextView txtArea;
        final TextView txtPrice;
        final TextView txtLastApplyDate;
        final ImageView favoriteButton;
        final ImageView image;
        Accommodation current;
        int position;
        private RecyclerViewHolderObserver recyclerViewHolderObserver;

        public AccommodationRecyclerViewHolder(View v, final Class<? extends Activity> targetClass) {
            super(v);
            txtAddress = (TextView) v.findViewById(R.id.address);
            txtHouseType = (TextView) v.findViewById(R.id.type);
            txtArea = (TextView) v.findViewById(R.id.area);
            txtPrice = (TextView) v.findViewById(R.id.price);
            txtLastApplyDate = (TextView) v.findViewById(R.id.lastApplyDate);
            favoriteButton = (ImageView) v.findViewById(R.id.favoriteButton);
            image = (ImageView) v.findViewById(R.id.image);

            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    notifyObservers();
                }
            });


            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    ObjectActivityModel.setStartPosition(position);
                    ActivitySwitcher.getInstance(v.getContext()).navigate(targetClass);
                    //Toast.makeText(v.getContext(), txtAddress.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public Accommodation getCurrent(){return current;}

        public boolean isFavorite() {
            return current.getFavorite();
        }

    @Override
    public void add(RecyclerViewHolderObserver recyclerViewHolderObserver) {
        this.recyclerViewHolderObserver = recyclerViewHolderObserver;
    }


    @Override
    public void notifyObservers() {
        try{
            recyclerViewHolderObserver.update(this);}
        catch(Exception e){
            System.out.print("Exception thrown in notifyObservers() in AccommodationRecyclerViewHolder)");
        }}


}
