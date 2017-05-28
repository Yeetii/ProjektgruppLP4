package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.view.View;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.view.MultiSpinner;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchList;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Host;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.HouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AdvancedSearchFragmentView;

/**
 * @author Erik Magnusson
 * revised by John Segerstedt
 * Used by: AdvancedSearchController, ModalController
 * Uses MultiSpinerController, MultiSpinner, Search, SearchList, Host,
 * AccomodationHouseType, Region, AdvancedSearchFragmentView.
 * Responsibility: Controller for Advanced search fragement, handling the user actions when setting
 * different attributes in advanced search.
 */

public class AdvancedSearchFragmentController {
    private final View view;
    private SearchView advancedSearchView;

    private SeekBar seekBarMinPrice;
    private SeekBar seekBarMaxPrice;
    private SeekBar seekBarMinArea;
    private SeekBar seekBarMaxArea;
    private SeekBar seekBarDaysUploaded;
    private SeekBar seekBarDaysLeft;
    private TextView textViewMinPrice;
    private TextView textViewMaxPrice;
    private TextView textViewMinArea;
    private TextView textViewDaysUploaded;
    private TextView textViewDaysLeft;
    private TextView textViewMaxArea;
    private MultiSpinner houseTypeSpinner;
    private MultiSpinner regionSpinner;
    private MultiSpinner landlordSpinner;


    //Sets filters to last search
    public AdvancedSearchFragmentController(View view){
        this.view = view;
        new AdvancedSearchFragmentView(view);
        initListeners();
        fillFilters();
    }

    //Sets filters to provided search
    public AdvancedSearchFragmentController(View view, Search search){
        this.view = view;
        new AdvancedSearchFragmentView(view);
        initListeners();
        fillFilters(search);
    }

    private void initListeners(){
        seekBarMinPrice = (SeekBar) view.findViewById(R.id.seekBarMinPrice);
        seekBarMaxPrice = (SeekBar) view.findViewById(R.id.seekBarMaxPrice);
        seekBarMinArea = (SeekBar) view.findViewById(R.id.seekBarMinArea);
        seekBarMaxArea = (SeekBar) view.findViewById(R.id.seekBarMaxArea);
        seekBarDaysUploaded = (SeekBar) view.findViewById(R.id.seekBarDaysUploaded);
        seekBarDaysLeft = (SeekBar) view.findViewById(R.id.seekBarDaysLeft);

        textViewMinPrice = (TextView) view.findViewById(R.id.textViewMinPrice);
        textViewMaxPrice = (TextView) view.findViewById(R.id.textViewMaxPrice);
        textViewMinArea = (TextView) view.findViewById(R.id.textViewMinArea);
        textViewMaxArea = (TextView) view.findViewById(R.id.textViewMaxArea);
        textViewDaysUploaded = (TextView) view.findViewById(R.id.textViewDaysUploaded);
        textViewDaysLeft = (TextView) view.findViewById(R.id.textViewDaysLeft);

        houseTypeSpinner = (MultiSpinner) view.findViewById(R.id.roomType_spinner);
        regionSpinner = (MultiSpinner) view.findViewById(R.id.areas_spinner);
        landlordSpinner = (MultiSpinner) view.findViewById(R.id.landlord_spinner);

        advancedSearchView = (SearchView) view.findViewById(R.id.advancedSearchView);

        seekBarMinPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMinPrice));
        seekBarMaxPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMaxPrice));
        seekBarMinArea.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMinArea));
        seekBarMaxArea.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMaxArea));
        seekBarDaysUploaded.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewDaysUploaded));
        seekBarDaysLeft.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewDaysLeft));

        new MultiSpinnerController(houseTypeSpinner, HouseType.values());
        new MultiSpinnerController(regionSpinner, Region.values());
        new MultiSpinnerController(landlordSpinner, Host.values());
    }

    private void fillFilters() {
        fillFilters(SearchList.getLastSearch());
    }

    public void fillFilters(Search search){
        initMax();

        if(!search.isEmpty()){
            fillSeekBars(search);

            try {
                fillHouseTypeSpinner(search);
                fillRegionSpinner(search);
                fillLandlordSpinner(search);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        if(search.getMaxPrice() < 1){seekBarMaxPrice.setProgress(10000);}
        if(search.getMaxArea() < 1){seekBarMaxArea.setProgress(100);}
        if(search.getDaysUpploaded() < 0){
            seekBarDaysUploaded.setProgress(7);}
        if(search.getDaysLeft() < 0){seekBarDaysLeft.setProgress(7);}

        updateTextViews();
    }

    private void fillSeekBars(Search lastSearch){
        seekBarMinPrice.setProgress(lastSearch.getMinPrice());
        seekBarMaxPrice.setProgress(lastSearch.getMaxPrice());
        seekBarMinArea.setProgress(lastSearch.getMinArea());
        seekBarMaxArea.setProgress(lastSearch.getMaxArea());
        seekBarDaysUploaded.setProgress(lastSearch.getDaysUpploaded());
        seekBarDaysLeft.setProgress(lastSearch.getDaysLeft());
        advancedSearchView.setQuery(lastSearch.getMainSearch(), true);
    }

    private void fillHouseTypeSpinner(Search lastSearch) {
        try{
            houseTypeSpinner.clear();
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(HouseType.CORRIDOR)) {houseTypeSpinner.select(0);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(HouseType.KITCHENETTE)) {houseTypeSpinner.select(1);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(HouseType.COOKING_CABINET)) {houseTypeSpinner.select(2);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(HouseType.ONE_ROOM)) {houseTypeSpinner.select(3);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(HouseType.TWO_ROOMS)) {houseTypeSpinner.select(4);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(HouseType.TWO_ROOMS_KITCHENETTE)) {houseTypeSpinner.select(5);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(HouseType.THREE_ROOMS)) {houseTypeSpinner.select(6);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(HouseType.FOUR_ROOMS)) {houseTypeSpinner.select(7);}
        }catch(Exception e){
            houseTypeSpinner.selectAll();}
    }

    private void fillRegionSpinner(Search lastSearch) {
        regionSpinner.clear();
        try {
            regionSpinner.clear();
            if (lastSearch.getPossibleRegions().contains(Region.CENTER)) {
                regionSpinner.select(0);
            }
            if (lastSearch.getPossibleRegions().contains(Region.NORTH)) {
                regionSpinner.select(1);
            }
            if (lastSearch.getPossibleRegions().contains(Region.EAST)) {
                regionSpinner.select(2);
            }
            if (lastSearch.getPossibleRegions().contains(Region.WEST)) {
                regionSpinner.select(3);
            }
        }catch(Exception e){
            regionSpinner.selectAll();}
    }

    private void fillLandlordSpinner(Search lastSearch) {
        try {
            landlordSpinner.clear();
            if (lastSearch.getPossibleAccommodationHosts().contains(Host.CHALMERS)) {
                landlordSpinner.select(0);
            }
            if (lastSearch.getPossibleAccommodationHosts().contains(Host.SGS)) {
                landlordSpinner.select(1);
            }
        }catch(Exception e){
            landlordSpinner.selectAll();
        }
    }

    private void initMax() {
        seekBarMinPrice.setMax(10000);
        seekBarMaxPrice.setMax(10000);
        seekBarMinArea.setMax(100);
        seekBarMaxArea.setMax(100);
        seekBarDaysUploaded.setMax(7);
        seekBarDaysLeft.setMax(7);
    }

    private void updateTextViews() {
        textViewMinPrice.setText(Integer.toString(seekBarMinPrice.getProgress()));
        textViewMaxPrice.setText(Integer.toString(seekBarMaxPrice.getProgress()));
        textViewMinArea.setText(Integer.toString(seekBarMinArea.getProgress()));
        textViewMaxArea.setText(Integer.toString(seekBarMaxArea.getProgress()));
        textViewDaysUploaded.setText(Integer.toString(seekBarDaysUploaded.getProgress()));
        textViewDaysLeft.setText(Integer.toString(seekBarDaysLeft.getProgress()));
    }
    
    private SearchView.OnClickListener onClickListenerSearch = new SearchView.OnClickListener() {
        @Override
        public void onClick(View view) {
            SearchView searchView = (SearchView) view.findViewById(R.id.searchField);
            //switch (view.getId()) {
            //case R.id.searchField:
            searchView.onActionViewExpanded();
            //       break;
            //}
        }
    };

    public Search parseSearchTerms(boolean addToSearchHistory){
        String mainSearch = "";
        List<HouseType> possibleAccommodationHouseTypes = new ArrayList<>();
        List<Host> possibleAccommodationHosts = new ArrayList<>();
        List<Region> possibleRegions = new ArrayList<>();
        int minPrice = -1;
        int maxPrice = -1;
        int minArea = -1;
        int maxArea = -1;
        int daysUpploaded = -1;
        int daysLeft = -1;

        try{mainSearch = advancedSearchView.getQuery().toString();}catch(NullPointerException e){
            e.printStackTrace();
        }
        try{possibleAccommodationHouseTypes = HouseType.parseStringList(houseTypeSpinner.getSelectedItems());}catch(NullPointerException e){
            e.printStackTrace();
        }
        try{possibleAccommodationHosts = Host.parseStringList(landlordSpinner.getSelectedItems());}catch(NullPointerException e){
            e.printStackTrace();
        }
        try{possibleRegions = Region.parseStringList(regionSpinner.getSelectedItems());}catch(NullPointerException e){
            e.printStackTrace();
        }
        try{minPrice = seekBarMinPrice.getProgress();}catch(NullPointerException e){
            e.printStackTrace();
        }
        try{maxPrice = seekBarMaxPrice.getProgress();}catch(NullPointerException e){
            e.printStackTrace();
        }
        try{minArea = seekBarMinArea.getProgress();}catch(NullPointerException e){
            e.printStackTrace();
        }
        try{maxArea = seekBarMaxArea.getProgress();}catch(NullPointerException e){
            e.printStackTrace();
        }
        try{daysUpploaded = seekBarDaysUploaded.getProgress();}catch(NullPointerException e){
            e.printStackTrace();
        }
        try{daysLeft = seekBarDaysLeft.getProgress();}catch(NullPointerException e){
            e.printStackTrace();
        }

        return SearchList.createSearch(mainSearch, possibleAccommodationHouseTypes,
                possibleAccommodationHosts, possibleRegions, minPrice, maxPrice,
                minArea, maxArea, daysUpploaded, daysLeft, addToSearchHistory);
    }

    //Custom listener that updates a textView with the seekbars progress
    private class OnSeekBarChangeListenerText implements SeekBar.OnSeekBarChangeListener {

        private final TextView text;

        OnSeekBarChangeListenerText(TextView text){
            this.text = text;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            text.setText("" + i);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
