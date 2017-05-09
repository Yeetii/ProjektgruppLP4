package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SearchView;

import com.db4o.internal.Null;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.activity.FavoritesActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.MainSearchActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.MultiSpinner;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Region;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;


public class AdvancedSearchActivityController {
    private Activity activity;
    private Button advancedSearchButton;
    private SearchView advancedSearchView;

    private SeekBar seekBarMinPrice;
    private SeekBar seekBarMaxPrice;
    private SeekBar seekBarMinArea;
    private SeekBar seekBarMaxArea;
    private TextView textViewMinPrice;
    private TextView textViewMaxPrice;
    private TextView textViewMinArea;
    private TextView textViewMaxArea;
    private MultiSpinner roomTypeSpinner;
    private MultiSpinner areasSpinner;
    private MultiSpinner landlordSpinner;


    public AdvancedSearchActivityController(Activity activity){
        this.activity = activity;
        initListeners();
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

        roomTypeSpinner = (MultiSpinner) activity.findViewById(R.id.roomType_spinner);
        areasSpinner = (MultiSpinner) activity.findViewById(R.id.areas_spinner);
        landlordSpinner = (MultiSpinner) activity.findViewById(R.id.landlord_spinner);

        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ImageButton cancelButton = (ImageButton) activity.findViewById(R.id.cancel);
        advancedSearchButton = (Button) activity.findViewById(R.id.advancedSearchButton);
        cancelButton.setOnClickListener(onClickListener);
        advancedSearchButton.setOnClickListener(onAdvancedSearchListener);

        advancedSearchView = (SearchView) activity.findViewById(R.id.advancedSearchView);

        seekBarMinPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMinPrice));
        seekBarMaxPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMaxPrice));
        seekBarMinArea.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMinArea));
        seekBarMaxArea.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMaxArea));

        roomTypeSpinner.setListener(new MultiSpinnerListener(AccommodationHouseType.values()));
        areasSpinner.setListener(new MultiSpinnerListener(Region.values()));
        landlordSpinner.setListener(new MultiSpinnerListener(AccommodationHost.values()));
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


            String mainSearch = "";
            ArrayList<AccommodationHouseType> possibleAccommodationHouseTypes = new ArrayList<>();
            ArrayList<AccommodationHost> possibleAccommodationHosts = new ArrayList<>();
            ArrayList<Region> possibleRegions = new ArrayList<>();
            int minPrice = -1;
            int maxPrice = -1;
            double minArea = -1;
            double maxArea = -1;

            // TODO: implement maxSearchers in AdvancedSearch. Dates? Address-field?
            int maxSearchers = -1;
            String upploadDate = "";
            String lastApplyDate = "";
            String address = "";

            try{mainSearch = advancedSearchView.getQuery().toString();}catch(NullPointerException e){}
            try{possibleAccommodationHouseTypes = AccommodationHouseType.parseStringList(roomTypeSpinner.getSelectedItems());}catch(NullPointerException e){}
            try{possibleAccommodationHosts = AccommodationHost.parseStringList(landlordSpinner.getSelectedItems());}catch(NullPointerException e){}
            try{possibleRegions = Region.parseStringList(areasSpinner.getSelectedItems());}catch(NullPointerException e){}
            try{minPrice = seekBarMinPrice.getProgress();}catch(NullPointerException e){}
            try{maxPrice = seekBarMaxPrice.getProgress();}catch(NullPointerException e){}
            try{minArea = seekBarMinArea.getProgress();}catch(NullPointerException e){}
            try{maxArea = seekBarMinArea.getProgress();}catch(NullPointerException e){}
            //try{maxSearchers = ???;}catch(NullPointerException e){}


            SearchHandler.createSearch(mainSearch, address, possibleAccommodationHouseTypes,
                    possibleAccommodationHosts, possibleRegions, minPrice, maxPrice,
                    minArea, maxArea, maxSearchers, upploadDate, lastApplyDate);

            //Är detta en bra lösning..?
            SearchActivityModel.getInstance().refreshAdapter();

            returnToMainSearch();
        }
    };

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
    public static void advancedSearchButtonPressed(View view){
        System.out.print("An advanced search was made!");
    }
}
