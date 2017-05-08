package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import se.chalmers.projektgrupplp4.studentlivinggbg.AdvancedSearchActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.FavoritesActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.MainSearchActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.MultiSpinner;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AccommodationHouseType;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

/**
 * Created by Erik on 2017-05-04.
 */

public class AdvancedSearchActivityController {
    private Activity activity;

    public AdvancedSearchActivityController(Activity activity){
        this.activity = activity;
        initListeners();
    }

    private void initListeners(){
        SeekBar seekBarMinPrice = (SeekBar) activity.findViewById(R.id.seekBarMinPrice);
        SeekBar seekBarMaxPrice = (SeekBar) activity.findViewById(R.id.seekBarMaxPrice);
        SeekBar seekBarMinArea = (SeekBar) activity.findViewById(R.id.seekBarMinArea);
        SeekBar seekBarMaxArea = (SeekBar) activity.findViewById(R.id.seekBarMaxArea);

        final TextView textViewMinPrice = (TextView) activity.findViewById(R.id.textViewMinPrice);
        final TextView textViewMaxPrice = (TextView) activity.findViewById(R.id.textViewMaxPrice);
        final TextView textViewMinArea = (TextView) activity.findViewById(R.id.textViewMinArea);
        final TextView textViewMaxArea = (TextView) activity.findViewById(R.id.textViewMaxArea);

        MultiSpinner roomTypeSpinner = (MultiSpinner) activity.findViewById(R.id.roomType_spinner);
        MultiSpinner areasSpinner = (MultiSpinner) activity.findViewById(R.id.areas_spinner);
        MultiSpinner landlordSpinner = (MultiSpinner) activity.findViewById(R.id.landlord_spinner);

        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ImageButton cancelButton = (ImageButton) activity.findViewById(R.id.cancel);
        cancelButton.setOnClickListener(onClickListener);

        seekBarMinPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMinPrice));
        seekBarMaxPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMaxPrice));
        seekBarMinArea.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMinArea));
        seekBarMaxArea.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMaxArea));

        roomTypeSpinner.setListener(new MultiSpinnerListener(AccommodationHouseType.values()));
        areasSpinner.setListener(new MultiSpinnerListener(AccommodationHouseType.values()));
        landlordSpinner.setListener(new MultiSpinnerListener(AccommodationHouseType.values()));
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
            Intent intent = new Intent(activity, MainSearchActivity.class);
            activity.startActivity(intent);
        }
    };

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
