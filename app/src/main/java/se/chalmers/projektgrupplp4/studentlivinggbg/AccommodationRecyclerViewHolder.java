package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Jonathan on 18/04/2017.
 */

public class AccommodationRecyclerViewHolder extends RecyclerView.ViewHolder implements RecyclerViewHolderObservable {
        TextView txtAddress;
        TextView txtHouseType;
        TextView txtArea;
        TextView txtPrice;
        TextView txtLastApplyDate;
        ImageView favoriteButton;
        ImageView image;
        Accommodation current;
        int position;
        private RecyclerViewHolderObserver recyclerViewHolderObserver;

        public AccommodationRecyclerViewHolder(View v) {
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
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ObjectActivity.class);
                    intent.putExtra("ARG_POSITION", position);
                    startActivity(context,intent,null);
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
            System.out.print("Exception thrown in notifyObservers() in AccomodationRecyclerViewHolder)");
        }}


}
