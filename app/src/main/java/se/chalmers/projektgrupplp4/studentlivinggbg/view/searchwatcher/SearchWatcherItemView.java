package se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher;

import android.view.View;
import android.widget.TextView;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Host;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.EnumHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * @author John Segerstedt
 * Used by: SearchWatcherAdapter, SearchWatcherController, SearchWatcherItemController
 * Uses: Host, EnumHelper, Region, SearchWatcher;
 * Responsibility: View for SearchWatcherItems
 */

public class SearchWatcherItemView {
    private boolean controllerAttached = false;
    private View view;
    private final TextView txtTitle;
    private final TextView txtSearchLabel;
    private final TextView txtSearch;
    private final TextView txtHouseTypeLabel;
    private final TextView txtHouseType;
    private final TextView txtArea;
    private final TextView txtAreaLabel;
    private final TextView txtPrice;
    private final TextView txtPriceLabel;
    private final TextView txtMatches;

    private SearchWatcher model;


    public SearchWatcherItemView(SearchWatcher model, View convertView) {
        this.model = model;
        this.view = convertView;

        this.txtTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
        this.txtSearch = (TextView) convertView.findViewById(R.id.textViewSearch);
        this.txtSearchLabel = (TextView) convertView.findViewById(R.id.textViewSearchLabel);
        this.txtHouseType = (TextView) convertView.findViewById(R.id.textViewHouseType);
        this.txtHouseTypeLabel = (TextView) convertView.findViewById(R.id.textViewHouseTypeLabel);
        this.txtArea = (TextView) convertView.findViewById(R.id.textViewArea);
        this.txtAreaLabel = (TextView) convertView.findViewById(R.id.textViewAreaLabel);
        this.txtPrice = (TextView) convertView.findViewById(R.id.textViewPrice);
        this.txtPriceLabel = (TextView) convertView.findViewById(R.id.textViewPriceLabel);
        txtMatches = (TextView) convertView.findViewById(R.id.textViewMatches);

        convertView.setTag(this);
    }

    public void updateView (SearchWatcher model) {
        this.model = model;
        updateTitle();
        updateMainSearch();
        updateAccommodationHouseType();
        updateArea();
        updatePrice();
        updateMatches();
    }

    private void updateTitle () {
        try {
            txtTitle.setText(model.getTitle());
        } catch (NullPointerException e) {
            txtTitle.setText("");
        }
    }

    private void updateMainSearch() {
        try{
            if(model.getSearch().getMainSearch() != null) txtSearch.setText(model.getSearch().getMainSearch());
        } catch(NullPointerException e){
            txtSearch.setText("");
            txtSearchLabel.setText("");
        }
    }

    private void updateAccommodationHouseType() {
        try{
            if(model.getSearch().getPossibleAccomodationHouseTypes().size() == 1){
                txtHouseType.setText(model.getSearch().getPossibleAccomodationHouseTypes().get(0).toString());
            }else if(model.getSearch().getPossibleRegions().size() < 3){
                txtHouseType.setText(EnumHelper.toString(model.getSearch().getPossibleRegions()));
            }else{
                txtHouseType.setText(Host.toStringShort(model.getSearch().getPossibleAccommodationHosts()));
            }
        }catch(NullPointerException e){
            txtHouseType.setText("");
            txtHouseTypeLabel.setText("");
        }
    }

    private void updateArea () {
        try{
            boolean a = model.getSearch().getMinArea() > 0 && model.getSearch().getMaxArea() < 100 && model.getSearch().getMaxArea() != -1;
            boolean b = model.getSearch().getMinArea() < 1 && (model.getSearch().getMaxArea() == 100 || model.getSearch().getMaxArea() == -1);

            if(a || b){
                txtArea.setText(model.getSearch().getMinArea() +
                        "-" + model.getSearch().getMaxArea());
            }

            else if(model.getSearch().getMinArea() < 1 && model.getSearch().getMaxArea() > -1){
                txtArea.setText("< " + model.getSearch().getMaxArea());
            }

            else if(model.getSearch().getMinArea() > -1 && model.getSearch().getMaxArea() == 100 && model.getSearch().getMaxArea() != -1){
                txtArea.setText("> " + model.getSearch().getMinArea());
            }

        }catch(NullPointerException e){
            txtArea.setText("");
            txtAreaLabel.setText("");
        }
    }

    private void updatePrice () {
        try{

            boolean a = model.getSearch().getMinPrice() > 0 && model.getSearch().getMaxPrice() < 10000 && model.getSearch().getMaxPrice() != -1;
            boolean b = model.getSearch().getMinPrice() < 1 && (model.getSearch().getMaxPrice() == 10000 || model.getSearch().getMaxPrice() == -1);

            if(a || b){
                txtPrice.setText(model.getSearch().getMinPrice() +
                        "-"+ model.getSearch().getMaxPrice());
            }

            else if(model.getSearch().getMinPrice() < 1 && model.getSearch().getMaxPrice() > -1){
                txtPrice.setText("< " + model.getSearch().getMaxPrice());
            }

            else if(model.getSearch().getMinPrice() > -1 && model.getSearch().getMaxPrice() == 10000 && model.getSearch().getMaxPrice() != -1){
                txtPrice.setText("> " + model.getSearch().getMinPrice());
            }

        }catch(NullPointerException e){
            txtPrice.setText("");
            txtPriceLabel.setText("");
        }
    }

    private void updateMatches() {
        try{
            txtMatches.setText("" + model.getNewMatches().size());
        }catch(NullPointerException e){
            txtMatches.setText("" + 0);
        }
    }

    public void setControllerAttached () {
        controllerAttached = true;
    }

    public boolean isControllerAttached() {
        return controllerAttached;
    }

    public SearchWatcher getModel () {
        return model;
    }

    public View getView () {
        return view;
    }

}
