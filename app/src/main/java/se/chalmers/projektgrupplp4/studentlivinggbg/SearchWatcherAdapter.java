package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.Model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.SearchWatcher;


/**
 * Created by PG on 03/04/2017.
 */

public class SearchWatcherAdapter extends ArrayAdapter<SearchWatcher> implements View.OnClickListener {

    private List<Search> dataSet;
    Context mContext;
    private int lastPosition = -1;

//    public SearchWatcherAdapter(List<Search> data, Context context) {
//        super(context, search_watcher_row_item, data);
//        this.dataSet = data;
//        this.mContext=context;
//    }

    public SearchWatcherAdapter(Context context, List<SearchWatcher> data) {
        super(context, R.layout.search_watcher_row_item, data);
    }

    private boolean isShowing = true;

    @Override
    public void onClick(View v) {
        SearchWatcherActivity.toggle();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SearchWatcher dataModel = getItem(position);
        SearchWatcherHolder viewHolder = new SearchWatcherHolder();


        if (convertView == null){

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.search_watcher_row_item, parent, false);

            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
            viewHolder.txtAddress = (TextView) convertView.findViewById(R.id.textViewAddress);
            viewHolder.txtAddressLabel = (TextView) convertView.findViewById(R.id.textViewAddressLabel);
            viewHolder.txtHouseType = (TextView) convertView.findViewById(R.id.textViewHouseType);
            viewHolder.txtHouseTypeLabel = (TextView) convertView.findViewById(R.id.textViewHouseTypeLabel);
            viewHolder.txtArea = (TextView) convertView.findViewById(R.id.textViewArea);
            viewHolder.txtAreaLabel = (TextView) convertView.findViewById(R.id.textViewAreaLabel);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.textViewPrice);
            viewHolder.txtPriceLabel = (TextView) convertView.findViewById(R.id.textViewPriceLabel);
            viewHolder.txtDays = (TextView) convertView.findViewById(R.id.textViewDays);
            viewHolder.txtDaysLabel = (TextView) convertView.findViewById(R.id.textViewDaysLabel);

            convertView.setTag(viewHolder);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.hamButton);
            System.out.println(imageView);
            imageView.setOnClickListener(this);
            SearchWatcherActivity.makeModalInvisible();
        }else{
            viewHolder = (SearchWatcherHolder) convertView.getTag();
        }


        lastPosition = position;


        try{                    //Title
            if(dataModel.getTitle() != null){
                viewHolder.txtTitle.setText(dataModel.getTitle());}
        }catch(NullPointerException e){
            viewHolder.txtTitle.setText("");
        }
        

        try{                    //Address
            if(dataModel.getSearch().getAddress() != null){
                viewHolder.txtAddress.setText(dataModel.getSearch().getAddress());}
        }catch(NullPointerException e){
            viewHolder.txtAddress.setText("");
            viewHolder.txtAddressLabel.setText("");
        }



        try{                //AccommodationHouseType
            if(dataModel.getSearch().getPossibleAccomodationHouseTypes() != null){
                if(dataModel.getSearch().getPossibleAccomodationHouseTypes().size() > 1){
                    viewHolder.txtHouseType.setText("Flera typer har valts.");
            }else{
                viewHolder.txtHouseType.setText(dataModel.getSearch().getPossibleAccomodationHouseTypes().get(0).toString());}}
        }catch(NullPointerException e){
            viewHolder.txtHouseType.setText("");
            viewHolder.txtHouseTypeLabel.setText("");
        }

        try{                //Area
            if(dataModel.getSearch().getMinArea() > -1 && dataModel.getSearch().getMaxArea() > -1){
                viewHolder.txtArea.setText(String.valueOf(dataModel.getSearch().getMinArea())+
                        "-"+String.valueOf(dataModel.getSearch().getMaxArea()));}
            
            else if(dataModel.getSearch().getMinArea() < 0 && dataModel.getSearch().getMaxArea() > -1){
                viewHolder.txtArea.setText("< "+String.valueOf(dataModel.getSearch().getMaxArea()));}
            
            else if(dataModel.getSearch().getMinArea() > -1 && dataModel.getSearch().getMaxArea() < 0){
                viewHolder.txtArea.setText("> "+String.valueOf(dataModel.getSearch().getMinArea()));}

        }catch(NullPointerException e){
            viewHolder.txtArea.setText("");
            viewHolder.txtAreaLabel.setText("");
        }

        try{                //Price
            if(dataModel.getSearch().getMinPrice() > -1 && dataModel.getSearch().getMaxPrice() > -1){
                viewHolder.txtPrice.setText(String.valueOf(dataModel.getSearch().getMinPrice())+
                        "-"+String.valueOf(dataModel.getSearch().getMaxPrice()));}

            else if(dataModel.getSearch().getMinPrice() < 0 && dataModel.getSearch().getMaxPrice() > -1){
                viewHolder.txtPrice.setText("< "+String.valueOf(dataModel.getSearch().getMaxPrice()));}

            else if(dataModel.getSearch().getMinPrice() > -1 && dataModel.getSearch().getMaxPrice() < 0){
                viewHolder.txtPrice.setText("> "+String.valueOf(dataModel.getSearch().getMinPrice()));}

        }catch(NullPointerException e){
            viewHolder.txtPrice.setText("");
            viewHolder.txtPriceLabel.setText("");
        }

        //TODO: Days Left
        viewHolder.txtDays.setText("3");


        // Return the completed view to render on screen



        return convertView;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        convertView = super.getView(position, convertView, parent);
//        if (convertView != null) {
//            ImageView imageView = (ImageView) convertView.findViewById(R.id.hamButton);
//            System.out.println(imageView);
//            imageView.setOnClickListener(this);
//            SearchWatcherActivity.makeModalInvisible();
//        }
//        return convertView;
//    }
}

















