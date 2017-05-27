package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.ImageModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.ObjectActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.ActivitySwitcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;

/**
 * Created by Jonathan on 18/04/2017.
 * @author John
 * revised by Jonathan, Erik
 * John made the original ListvVew implementation
 * and Jonathan redid it as an RecyclerView
 * Used by: AccommodationRecyclerViewAdapter, RecyclerViewHelperController, RecyclerViewHolderObserver
 * Uses: ObjectActivityModel, ActivitySwitcher, Accommodation
 * Responsibility: View for row items
 */

public class AccommodationRecyclerViewHolder extends RecyclerView.ViewHolder {
    final TextView txtAddress;
    final TextView txtHouseType;
    final TextView txtArea;
    final TextView txtPrice;
    final TextView txtLastApplyDate;
    final ImageView favoriteButton;
    final ImageView image;
    Accommodation current;
    int position;

    public AccommodationRecyclerViewHolder(View v, final Class<? extends Activity> targetClass) {
        super(v);
        txtAddress = (TextView) v.findViewById(R.id.address);
        txtHouseType = (TextView) v.findViewById(R.id.type);
        txtArea = (TextView) v.findViewById(R.id.area);
        txtPrice = (TextView) v.findViewById(R.id.price);
        txtLastApplyDate = (TextView) v.findViewById(R.id.lastApplyDate);
        favoriteButton = (ImageView) v.findViewById(R.id.favoriteButton);
        image = (ImageView) v.findViewById(R.id.image);
    }

    public void whenBound(){
        txtAddress.setText(current.getAddress());
        txtHouseType.setText(current.getAccommodationHouseType());
        txtArea.setText(current.getArea());
        txtPrice.setText(current.getPrice());
        txtLastApplyDate.setText(current.getLastApplyDate());
        image.setImageDrawable(ImageModel.<Drawable>getInstance().getMainImage(current.getImagePath()));
    }
    
    public Accommodation getCurrent(){return current;}
    
    public void setCurrent(Accommodation current){
        this.current = current;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isFavorite() {
        return current.getFavorite();
    }

    public void setButton(boolean favorite) {
        int drawable = favorite ? R.drawable.favorite_on : R.drawable.favorite_off;
        favoriteButton.setImageResource(drawable);
    }

}
