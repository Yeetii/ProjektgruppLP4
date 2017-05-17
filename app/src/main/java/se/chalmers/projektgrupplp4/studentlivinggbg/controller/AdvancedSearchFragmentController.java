package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.view.View;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;

import se.chalmers.projektgrupplp4.studentlivinggbg.MultiSpinner;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;


public class AdvancedSearchFragmentController {
    private Activity activity;
    private SearchView advancedSearchView;

    private SeekBar seekBarMinPrice;
    private SeekBar seekBarMaxPrice;
    private SeekBar seekBarMinArea;
    private SeekBar seekBarMaxArea;
    private TextView textViewMinPrice;
    private TextView textViewMaxPrice;
    private TextView textViewMinArea;
    private TextView textViewMaxArea;
    private MultiSpinner houseTypeSpinner;
    private MultiSpinner regionSpinner;
    private MultiSpinner landlordSpinner;


    //Sets filters to last search
    public AdvancedSearchFragmentController(Activity activity){
        this.activity = activity;
        initListeners();
        fillFilters();
    }

    //Sets filters to provided search
    public AdvancedSearchFragmentController(Activity activity, Search search){
        this.activity = activity;
        initListeners();
        fillFilters(search);
    }

    private void initListeners(){
        seekBarMinPrice = (SeekBar) activity.findViewById(R.id.seekBarMinPrice);
        seekBarMaxPrice = (SeekBar) activity.findViewById(R.id.seekBarMaxPrice);
        seekBarMinArea = (SeekBar) activity.findViewById(R.id.seekBarMinArea);
        seekBarMaxArea = (SeekBar) activity.findViewById(R.id.seekBarMaxArea);

        textViewMinPrice = (TextView) activity.findViewById(R.id.textViewMinPrice);
        textViewMaxPrice = (TextView) activity.findViewById(R.id.textViewMaxPrice);
        textViewMinArea = (TextView) activity.findViewById(R.id.textViewMinArea);
        textViewMaxArea = (TextView) activity.findViewById(R.id.textViewMaxArea);

        houseTypeSpinner = (MultiSpinner) activity.findViewById(R.id.roomType_spinner);
        regionSpinner = (MultiSpinner) activity.findViewById(R.id.areas_spinner);
        landlordSpinner = (MultiSpinner) activity.findViewById(R.id.landlord_spinner);

        advancedSearchView = (SearchView) activity.findViewById(R.id.advancedSearchView);

        seekBarMinPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMinPrice));
        seekBarMaxPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMaxPrice));
        seekBarMinArea.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMinArea));
        seekBarMaxArea.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMaxArea));

        houseTypeSpinner.setListener(new MultiSpinnerListener(AccommodationHouseType.values()));
        regionSpinner.setListener(new MultiSpinnerListener(Region.values()));
        landlordSpinner.setListener(new MultiSpinnerListener(AccommodationHost.values()));
    }

    private void fillFilters() {
        fillFilters(SearchHandler.getLastSearch());
    }

    public void fillFilters(Search search){

        System.out.println("Filling filters");
        initMax();

        if(!search.isEmpty()){
            //todo These could be more dynamic + dublicated code?

            fillSeekBars(search);
            fillHouseTypeSpinner(search);
            fillRegionSpinner(search);
            fillLandlordSpinner(search);

        }
        if(search.getMaxPrice() < 1){seekBarMaxPrice.setProgress(15000);}
        if(search.getMaxArea() < 1){seekBarMaxArea.setProgress(100);}

        updateTextViews();
    }

    private void fillSeekBars(Search lastSearch){
        seekBarMinPrice.setProgress(lastSearch.getMinPrice());
        seekBarMaxPrice.setProgress(lastSearch.getMaxPrice());
        seekBarMinArea.setProgress(lastSearch.getMinArea());
        seekBarMaxArea.setProgress(lastSearch.getMaxArea());
        advancedSearchView.setQuery(lastSearch.getMainSearch(), true);
    }

    private void fillHouseTypeSpinner(Search lastSearch) {
        houseTypeSpinner.clear();
        try{
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.CORRIDOR)) {houseTypeSpinner.select(0);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.KITCHENETTE)) {houseTypeSpinner.select(1);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.COOKING_CABINET)) {houseTypeSpinner.select(2);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.ONE_ROOM)) {houseTypeSpinner.select(3);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.TWO_ROOMS)) {houseTypeSpinner.select(4);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.TWO_ROOMS_KITCHENETTE)) {houseTypeSpinner.select(5);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.THREE_ROOMS)) {houseTypeSpinner.select(6);}
            if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.FOUR_ROOMS)) {houseTypeSpinner.select(7);}
        }catch(Exception e){
            houseTypeSpinner.selectAll();}
    }

    private void fillRegionSpinner(Search lastSearch) {
        regionSpinner.clear();
        try {
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
        landlordSpinner.clear();
        try {
            if (lastSearch.getPossibleAccommodationHosts().contains(AccommodationHost.CHALMERS)) {
                landlordSpinner.select(0);
            }
            if (lastSearch.getPossibleAccommodationHosts().contains(AccommodationHost.SGS)) {
                landlordSpinner.select(1);
            }
        }catch(Exception e){
            landlordSpinner.selectAll();
        }
    }

    private void initMax() {
        seekBarMinPrice.setMax(15000);
        seekBarMaxPrice.setMax(15000);
        seekBarMinArea.setMax(100);
        seekBarMaxArea.setMax(100);
    }

    private void updateTextViews() {
        textViewMinPrice.setText(Integer.toString(seekBarMinPrice.getProgress()));
        textViewMaxPrice.setText(Integer.toString(seekBarMaxPrice.getProgress()));
        textViewMinArea.setText(Integer.toString(seekBarMinArea.getProgress()));
        textViewMaxArea.setText(Integer.toString(seekBarMaxArea.getProgress()));
    }
    
    private SearchView.OnClickListener onClickListenerSearch = new SearchView.OnClickListener() {
        @Override
        public void onClick(View view) {
            SearchView searchView = (SearchView) activity.findViewById(R.id.searchField);
            //switch (view.getId()) {
            //case R.id.searchField:
            searchView.onActionViewExpanded();
            //       break;
            //}
        }
    };

    public Search parseSearchTerms(boolean addToSearchHistory){
        String mainSearch = "";
        ArrayList<AccommodationHouseType> possibleAccommodationHouseTypes = new ArrayList<>();
        ArrayList<AccommodationHost> possibleAccommodationHosts = new ArrayList<>();
        ArrayList<Region> possibleRegions = new ArrayList<>();
        int minPrice = -1;
        int maxPrice = -1;
        int minArea = -1;
        int maxArea = -1;

        // TODO: implement maxSearchers in AdvancedSearch. Dates? Address-field?
        int maxSearchers = -1;
        String upploadDate = "";
        String lastApplyDate = "";
        String address = "";

        try{mainSearch = advancedSearchView.getQuery().toString();}catch(NullPointerException e){}
        try{possibleAccommodationHouseTypes = AccommodationHouseType.parseStringList(houseTypeSpinner.getSelectedItems());}catch(NullPointerException e){}
        try{possibleAccommodationHosts = AccommodationHost.parseStringList(landlordSpinner.getSelectedItems());}catch(NullPointerException e){}
        try{possibleRegions = Region.parseStringList(regionSpinner.getSelectedItems());}catch(NullPointerException e){}
        try{minPrice = seekBarMinPrice.getProgress();}catch(NullPointerException e){}
        try{maxPrice = seekBarMaxPrice.getProgress();}catch(NullPointerException e){}
        try{minArea = seekBarMinArea.getProgress();}catch(NullPointerException e){}
        try{maxArea = seekBarMaxArea.getProgress();}catch(NullPointerException e){}
        //try{maxSearchers = ???;}catch(NullPointerException e){}

        return SearchHandler.createSearch(mainSearch, address, possibleAccommodationHouseTypes,
                possibleAccommodationHosts, possibleRegions, minPrice, maxPrice,
                minArea, maxArea, maxSearchers, upploadDate, lastApplyDate, addToSearchHistory);
    }

    //Custom listener that updates a textView with the seekbars progress
    private class OnSeekBarChangeListenerText implements SeekBar.OnSeekBarChangeListener {

        private TextView text;

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

    private class MultiSpinnerListener implements MultiSpinner.MultiSpinnerListener{

        private Enum[] terms;

        MultiSpinnerListener(Enum[] terms) {
            this.terms = terms;
        }

        @Override
        public void onItemsSelected(boolean[] selected) {
            //TODO do something with the terms that match with selected
            int i = 0;
            for (boolean b : selected){
                if (b)
                    System.out.println(terms[i]);
                i++;
            }
        }
    }
}
