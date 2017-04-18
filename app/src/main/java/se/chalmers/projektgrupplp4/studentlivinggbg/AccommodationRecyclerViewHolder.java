package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import se.chalmers.projektgrupplp4.studentlivinggbg.Model.Accommodation;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Jonathan on 18/04/2017.
 */

public class AccommodationRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtAddress;
        TextView txtHouseType;
        TextView txtArea;
        TextView txtPrice;
        TextView txtSearchers;
        ImageView favourite;
        ImageView image;
        Accommodation current;
        int position;

        public AccommodationRecyclerViewHolder(View v) {
            super(v);
            txtAddress = (TextView) v.findViewById(R.id.address);
            txtHouseType = (TextView) v.findViewById(R.id.type);
            txtArea = (TextView) v.findViewById(R.id.area);
            txtPrice = (TextView) v.findViewById(R.id.price);
            txtSearchers = (TextView) v.findViewById(R.id.searchers);
            favourite = (ImageView) v.findViewById(R.id.favourite);
            image = (ImageView) v.findViewById(R.id.image);
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

        public boolean isFavorite() {
            return current.getFavorite();
        }
    }
