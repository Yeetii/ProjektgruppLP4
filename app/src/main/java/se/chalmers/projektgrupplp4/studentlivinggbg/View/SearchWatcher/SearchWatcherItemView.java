package se.chalmers.projektgrupplp4.studentlivinggbg.View.SearchWatcher;

import android.view.View;
import android.widget.TextView;

import se.chalmers.projektgrupplp4.studentlivinggbg.Model.SearchWatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;


public class SearchWatcherItemView {
    private TextView txtTitle;
    private TextView txtAddressLabel;
    private TextView txtAddress;
    private TextView txtHouseTypeLabel;
    private TextView txtHouseType;
    private TextView txtArea;
    private TextView txtAreaLabel;
    private TextView txtPrice;
    private TextView txtPriceLabel;

    private SearchWatcherItem model;


    public SearchWatcherItemView(SearchWatcherItem model, View convertView) {
        this.model = model;

        this.txtTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
        this.txtAddress = (TextView) convertView.findViewById(R.id.textViewAddress);
        this.txtAddressLabel = (TextView) convertView.findViewById(R.id.textViewAddressLabel);
        this.txtHouseType = (TextView) convertView.findViewById(R.id.textViewHouseType);
        this.txtHouseTypeLabel = (TextView) convertView.findViewById(R.id.textViewHouseTypeLabel);
        this.txtArea = (TextView) convertView.findViewById(R.id.textViewArea);
        this.txtAreaLabel = (TextView) convertView.findViewById(R.id.textViewAreaLabel);
        this.txtPrice = (TextView) convertView.findViewById(R.id.textViewPrice);
        this.txtPriceLabel = (TextView) convertView.findViewById(R.id.textViewPriceLabel);

        convertView.setTag(this);
    }

    public void updateView () {
        updateTitle();
        updateAddress();
        updateAccommodationHouseType();
        updateArea();
        updatePrice();
    }

    private void updateTitle () {
        try {
            txtTitle.setText(model.getTitle());
        } catch (NullPointerException e) {
            txtTitle.setText("");
        }
    }

    private void updateAddress() {
        try{
            if(model.getSearch().getAddress() != null) txtAddress.setText(model.getSearch().getAddress());
        } catch(NullPointerException e){
            txtAddress.setText("");
            txtAddressLabel.setText("");
        }
    }

    private void updateAccommodationHouseType() {
        try{
            if(model.getSearch().getPossibleAccomodationHouseTypes() != null){
                if(model.getSearch().getPossibleAccomodationHouseTypes().size() > 1){
                    txtHouseType.setText("SGS, Chalmers");
                }else{
                    txtHouseType.setText(model.getSearch().getPossibleAccomodationHouseTypes().get(0).toString());
                }
            }
        }catch(NullPointerException e){
            txtHouseType.setText("");
            txtHouseTypeLabel.setText("");
        }
    }

    private void updateArea () {
        try{
            if(model.getSearch().getMinArea() > -1 && model.getSearch().getMaxArea() > -1){
                txtArea.setText(String.valueOf(model.getSearch().getMinArea())+
                        "-"+String.valueOf(model.getSearch().getMaxArea()));
            }

            else if(model.getSearch().getMinArea() < 0 && model.getSearch().getMaxArea() > -1){
                txtArea.setText("< "+String.valueOf(model.getSearch().getMaxArea()));
            }

            else if(model.getSearch().getMinArea() > -1 && model.getSearch().getMaxArea() < 0){
                txtArea.setText("> "+String.valueOf(model.getSearch().getMinArea()));
            }

        }catch(NullPointerException e){
            txtArea.setText("");
            txtAreaLabel.setText("");
        }
    }

    private void updatePrice () {
        try{
            if(model.getSearch().getMinPrice() > -1 && model.getSearch().getMaxPrice() > -1){
                txtPrice.setText(String.valueOf(model.getSearch().getMinPrice())+
                        "-"+String.valueOf(model.getSearch().getMaxPrice()));}

            else if(model.getSearch().getMinPrice() < 0 && model.getSearch().getMaxPrice() > -1){
                txtPrice.setText("< "+String.valueOf(model.getSearch().getMaxPrice()));
            }

            else if(model.getSearch().getMinPrice() > -1 && model.getSearch().getMaxPrice() < 0){
                txtPrice.setText("> "+String.valueOf(model.getSearch().getMinPrice()));
            }

        }catch(NullPointerException e){
            txtPrice.setText("");
            txtPriceLabel.setText("");
        }
    }

}
