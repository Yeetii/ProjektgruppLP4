package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.ImageModel;


public class AccommodationListViewAdapter extends ArrayAdapter<Accommodation> implements View.OnClickListener{

    private List<Accommodation> dataSet;
    Context mContext;
    private int lastPosition = -1;

    public AccommodationListViewAdapter(Context context, List<Accommodation> data) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;
    }


    //When tapping on the star
    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Accommodation tappedAccomodation=(Accommodation)getItem(position);


        switch (v.getId())
        {
            case R.id.favourite:
                //Tapping on the favorite star results in the accommodation
                // getting added to the list of favorites.
                changeFavoriteStatus(tappedAccomodation, v);
                break;
        }
    }

    public List<Accommodation> getDataSet() {
        return dataSet;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Accommodation dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        AccommodationListViewHolder viewHolder; // view lookup cache stored in tag


        if (convertView == null) {

            viewHolder = new AccommodationListViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);

            viewHolder.txtAddress = (TextView) convertView.findViewById(R.id.address);
            viewHolder.txtHouseType = (TextView) convertView.findViewById(R.id.type);
            viewHolder.txtArea = (TextView) convertView.findViewById(R.id.area);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.price);
            viewHolder.txtSearchers = (TextView) convertView.findViewById(R.id.searchers);
            viewHolder.favourite = (ImageView) convertView.findViewById(R.id.favourite);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AccommodationListViewHolder) convertView.getTag();
        }


        lastPosition = position;


        viewHolder.txtAddress.setText(dataModel.getAddress());
        viewHolder.txtHouseType.setText(dataModel.getAccommodationHouseType());
        viewHolder.txtArea.setText(dataModel.getArea());
        viewHolder.txtPrice.setText(dataModel.getPrice());
        viewHolder.txtSearchers.setText(dataModel.getSearchers());
        viewHolder.image.setImageDrawable(dataModel.getImage());
        viewHolder.favourite.setOnClickListener(this);
        viewHolder.favourite.setTag(position);
        // Return the completed view to render on screen

        try{updateFavoriteView(dataModel, convertView);}catch(Exception e){}
        return convertView;
    }


    private void changeFavoriteStatus(Accommodation dataModel, View v) {
        dataModel.changeFavoriteStatus();
        updateFavoriteView(dataModel, v);
    }

    private void updateFavoriteView(Accommodation dataModel, View v) {
        if(dataModel.getFavorite()){
            addAccomodationToFavorites(dataModel, v);
        }
        else {
            removeAccomodationFromFavorites(dataModel, v);
        }
    }

    //Should be refactored imo, should be enough with a toggle favorite method.
    private void addAccomodationToFavorites(Accommodation tappedAccomodation, View v) {
        tappedAccomodation.addAsFavorite();
        v.findViewById(R.id.favourite).setBackgroundResource(R.drawable.favorite_on);
        Snackbar.make(v, tappedAccomodation.getAddress() + " tillagd till favoriter.", Snackbar.LENGTH_LONG).setAction("No action", null).show();
    }

    private void removeAccomodationFromFavorites(Accommodation tappedAccomodation, View v) {
        tappedAccomodation.removeAsFavorite();
        v.findViewById(R.id.favourite).setBackgroundResource(R.drawable.favorite_off);
        Snackbar.make(v, tappedAccomodation.getAddress() + " borttagen från favoriter.", Snackbar.LENGTH_LONG).setAction("No action", null).show();
    }



}
