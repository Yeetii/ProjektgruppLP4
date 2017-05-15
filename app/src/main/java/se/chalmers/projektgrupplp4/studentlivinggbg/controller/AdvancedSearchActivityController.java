package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SearchView;
import java.util.ArrayList;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.FavoritesActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.MainSearchActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.MultiSpinner;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;


public class AdvancedSearchActivityController {
    private Activity activity;
    private Button advancedSearchButton;
    private Button createSearchWatcherButton;
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

    //While waiting for a name to become a SearchWatcher
    //TODO Should this be in the model?
    private Search wannabeSearchWatcher;


    public AdvancedSearchActivityController(Activity activity){
        this.activity = activity;
        initListeners();
        fillFilters();
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

        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ImageButton cancelButton = (ImageButton) activity.findViewById(R.id.cancel);
        advancedSearchButton = (Button) activity.findViewById(R.id.advancedSearchButton);
        createSearchWatcherButton = (Button) activity.findViewById(R.id.advancedSearchCreateSearchWatcherButton);

        cancelButton.setOnClickListener(onClickListener);
        advancedSearchButton.setOnClickListener(onAdvancedSearchListener);
        createSearchWatcherButton.setOnClickListener(onCreateSearchWatcherListener);

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
        Search lastSearch = SearchHandler.getLastSearch();
        initMax();

        if(!lastSearch.isEmpty()){
            //todo These could be more dynamic + dublicated code?

            fillSeekBars(lastSearch);
            fillHouseTypeSpinner(lastSearch);
            fillRegionSpinner(lastSearch);
            fillLandlordSpinner(lastSearch);

        }
        if(lastSearch.getMaxPrice() < 1){seekBarMaxPrice.setProgress(15000);}
        if(lastSearch.getMaxArea() < 1){seekBarMaxArea.setProgress(100);}

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
        if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.CORRIDOR)) {houseTypeSpinner.select(0);}
        if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.KITCHENETTE)) {houseTypeSpinner.select(1);}
        if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.COOKING_CABINET)) {houseTypeSpinner.select(2);}
        if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.ONE_ROOM)) {houseTypeSpinner.select(3);}
        if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.TWO_ROOMS)) {houseTypeSpinner.select(4);}
        if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.TWO_ROOMS_KITCHENETTE)) {houseTypeSpinner.select(5);}
        if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.THREE_ROOMS)) {houseTypeSpinner.select(6);}
        if (lastSearch.getPossibleAccomodationHouseTypes().contains(AccommodationHouseType.FOUR_ROOMS)) {houseTypeSpinner.select(7);}
    }

    private void fillRegionSpinner(Search lastSearch) {
        regionSpinner.clear();
        if (lastSearch.getPossibleRegions().contains(Region.CENTER)) {regionSpinner.select(0);}
        if (lastSearch.getPossibleRegions().contains(Region.NORTH)) {regionSpinner.select(1);}
        if (lastSearch.getPossibleRegions().contains(Region.EAST)) {regionSpinner.select(2);}
        if (lastSearch.getPossibleRegions().contains(Region.SOUTH)) {regionSpinner.select(3);}
        if (lastSearch.getPossibleRegions().contains(Region.WEST)) {regionSpinner.select(4);}
    }

    private void fillLandlordSpinner(Search lastSearch) {
        landlordSpinner.clear();
        if(lastSearch.getPossibleAccommodationHosts().contains(AccommodationHost.CHALMERS)){landlordSpinner.select(0);}
        if(lastSearch.getPossibleAccommodationHosts().contains(AccommodationHost.SGS)){landlordSpinner.select(1);}
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

    private ImageButton.OnClickListener onClickListener = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            returnToMainSearch();
        }
    };

    private Button.OnClickListener onAdvancedSearchListener = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            parseSearchTerms(true);

            //Är detta en bra lösning..?
            SearchActivityModel.getInstance().refreshAdapter();

            returnToMainSearch();
        }
    };

    private Button.OnClickListener onCreateSearchWatcherListener = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            System.out.println("Creating seardhwathcer");
            Search search = parseSearchTerms(false);
            //TODO prompt asking for name

            //Saves the search anc waits for nameDialog to finish
            createNameDialog();


        }
    };

    private void createNameDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage("Välj ett namn för din bevakning.").setTitle("Skapa bevaking");
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_search_watcher_name, null);
        builder.setView(dialogView);
        builder.setPositiveButton(R.string.dialogSearchWatcherOk, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                EditText text = (EditText) dialogView.findViewById(R.id.dialogSearchWatcherName);
                SearchWatcherModel.createSearchWatcher(text.getText().toString(), wannabeSearchWatcher);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private Search parseSearchTerms(boolean addToSearchHistory){
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

    private void returnToMainSearch(){
        Intent intent = new Intent(activity, MainSearchActivity.class);
        activity.startActivity(intent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    return true;
                case R.id.navigation_favorites:
                    Intent favorites = new Intent(activity, FavoritesActivity.class);
                    favorites.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    activity.startActivity(favorites);
                    return true;
                case R.id.navigation_notifications:
                    Intent searchWatcher = new Intent(activity, SearchWatcherActivity.class);
                    searchWatcher.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    activity.startActivity(searchWatcher);
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }

    };
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
