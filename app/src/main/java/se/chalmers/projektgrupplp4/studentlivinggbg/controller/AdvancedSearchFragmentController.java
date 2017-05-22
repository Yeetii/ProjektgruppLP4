package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

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
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AdvancedSearchFragmentView;


public class AdvancedSearchFragmentController {
    private final View view;
    private SearchView advancedSearchView;

    private SeekBar seekBarMinPrice;
    private SeekBar seekBarMaxPrice;
    private SeekBar seekBarMinArea;
    private SeekBar seekBarMaxArea;
    private SeekBar seekBarDaysUpploaded;
    private SeekBar seekBarDaysLeft;
    private TextView textViewMinPrice;
    private TextView textViewMaxPrice;
    private TextView textViewMinArea;
    private TextView textViewDaysUpploaded;
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
        initListeners();
        fillFilters(search);
    }

    private void initListeners(){
        seekBarMinPrice = (SeekBar) view.findViewById(R.id.seekBarMinPrice);
        seekBarMaxPrice = (SeekBar) view.findViewById(R.id.seekBarMaxPrice);
        seekBarMinArea = (SeekBar) view.findViewById(R.id.seekBarMinArea);
        seekBarMaxArea = (SeekBar) view.findViewById(R.id.seekBarMaxArea);
        seekBarDaysUpploaded = (SeekBar) view.findViewById(R.id.seekBarDaysUpploaded);
        seekBarDaysLeft = (SeekBar) view.findViewById(R.id.seekBarDaysLeft);

        textViewMinPrice = (TextView) view.findViewById(R.id.textViewMinPrice);
        textViewMaxPrice = (TextView) view.findViewById(R.id.textViewMaxPrice);
        textViewMinArea = (TextView) view.findViewById(R.id.textViewMinArea);
        textViewMaxArea = (TextView) view.findViewById(R.id.textViewMaxArea);
        textViewDaysUpploaded = (TextView) view.findViewById(R.id.textViewDaysUpploaded);
        textViewDaysLeft = (TextView) view.findViewById(R.id.textViewDaysLeft);

        houseTypeSpinner = (MultiSpinner) view.findViewById(R.id.roomType_spinner);
        regionSpinner = (MultiSpinner) view.findViewById(R.id.areas_spinner);
        landlordSpinner = (MultiSpinner) view.findViewById(R.id.landlord_spinner);

        advancedSearchView = (SearchView) view.findViewById(R.id.advancedSearchView);

        seekBarMinPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMinPrice));
        seekBarMaxPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMaxPrice));
        seekBarMinArea.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMinArea));
        seekBarMaxArea.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMaxArea));
        seekBarDaysUpploaded.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewDaysUpploaded));
        seekBarDaysLeft.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewDaysLeft));

        houseTypeSpinner.setListener(new MultiSpinnerListener(AccommodationHouseType.values()));
        regionSpinner.setListener(new MultiSpinnerListener(Region.values()));
        landlordSpinner.setListener(new MultiSpinnerListener(AccommodationHost.values()));
    }

    private void fillFilters() {
        fillFilters(SearchHandler.getLastSearch());
    }

    public void fillFilters(Search search){

        System.out.println("Filling filters" + search.getMaxPrice());
        initMax();

        if(!search.isEmpty()){
            fillSeekBars(search);

            try {
                fillHouseTypeSpinner(search);
                fillRegionSpinner(search);
                fillLandlordSpinner(search);
            }catch(Exception e){}

        }
        if(search.getMaxPrice() < 1){seekBarMaxPrice.setProgress(10000);}
        if(search.getMaxArea() < 1){seekBarMaxArea.setProgress(100);}
        if(search.getDaysUpploaded() < 0){seekBarDaysUpploaded.setProgress(7);}
        if(search.getDaysLeft() < 0){seekBarDaysLeft.setProgress(7);}

        updateTextViews();
    }

    private void fillSeekBars(Search lastSearch){
        seekBarMinPrice.setProgress(lastSearch.getMinPrice());
        seekBarMaxPrice.setProgress(lastSearch.getMaxPrice());
        seekBarMinArea.setProgress(lastSearch.getMinArea());
        seekBarMaxArea.setProgress(lastSearch.getMaxArea());
        seekBarDaysUpploaded.setProgress(lastSearch.getDaysUpploaded());
        seekBarDaysLeft.setProgress(lastSearch.getDaysLeft());
        advancedSearchView.setQuery(lastSearch.getMainSearch(), true);
    }

    private void fillHouseTypeSpinner(Search lastSearch) {
        try{
            houseTypeSpinner.clear();
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
        seekBarMinPrice.setMax(10000);
        seekBarMaxPrice.setMax(10000);
        seekBarMinArea.setMax(100);
        seekBarMaxArea.setMax(100);
        seekBarDaysUpploaded.setMax(7);
        seekBarDaysLeft.setMax(7);
    }

    private void updateTextViews() {
        textViewMinPrice.setText(Integer.toString(seekBarMinPrice.getProgress()));
        textViewMaxPrice.setText(Integer.toString(seekBarMaxPrice.getProgress()));
        textViewMinArea.setText(Integer.toString(seekBarMinArea.getProgress()));
        textViewMaxArea.setText(Integer.toString(seekBarMaxArea.getProgress()));
        textViewDaysUpploaded.setText(Integer.toString(seekBarDaysUpploaded.getProgress()));
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
        ArrayList<AccommodationHouseType> possibleAccommodationHouseTypes = new ArrayList<>();
        ArrayList<AccommodationHost> possibleAccommodationHosts = new ArrayList<>();
        ArrayList<Region> possibleRegions = new ArrayList<>();
        int minPrice = -1;
        int maxPrice = -1;
        int minArea = -1;
        int maxArea = -1;
        int daysUpploaded = -1;
        int daysLeft = -1;

        try{mainSearch = advancedSearchView.getQuery().toString();}catch(NullPointerException e){}
        try{possibleAccommodationHouseTypes = AccommodationHouseType.parseStringList(houseTypeSpinner.getSelectedItems());}catch(NullPointerException e){}
        try{possibleAccommodationHosts = AccommodationHost.parseStringList(landlordSpinner.getSelectedItems());}catch(NullPointerException e){}
        try{possibleRegions = Region.parseStringList(regionSpinner.getSelectedItems());}catch(NullPointerException e){}
        try{minPrice = seekBarMinPrice.getProgress();}catch(NullPointerException e){}
        try{maxPrice = seekBarMaxPrice.getProgress();}catch(NullPointerException e){}
        try{minArea = seekBarMinArea.getProgress();}catch(NullPointerException e){}
        try{maxArea = seekBarMaxArea.getProgress();}catch(NullPointerException e){}
        try{daysUpploaded = seekBarDaysUpploaded.getProgress();}catch(NullPointerException e){}
        try{daysLeft = seekBarDaysLeft.getProgress();}catch(NullPointerException e){}

        return SearchHandler.createSearch(mainSearch, possibleAccommodationHouseTypes,
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
