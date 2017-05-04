package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;

import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Region;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

public class AdvancedSearchActivity extends AppCompatActivity{

    private static SearchView searchView;


    //TODO add proper implementation with model
    ArrayList<String> roomTypeItems = new ArrayList<>();
    ArrayList<String> areasItems = new ArrayList<>();
    ArrayList<String> landlordItems = new ArrayList<>();

    MultiSpinner roomTypeSpinner;
    MultiSpinner areasSpinner;
    MultiSpinner landlordSpinner;

    SeekBar seekBarMinPrice;
    SeekBar seekBarMaxPrice;
    SeekBar seekBarMinArea;
    SeekBar seekBarMaxArea;


    private SearchView.OnClickListener onClickListenerSearch = new SearchView.OnClickListener() {
        @Override
        public void onClick(View view) {
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
            Intent intent = new Intent(AdvancedSearchActivity.this, MainSearchActivity.class);
            startActivity(intent);
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
                    Intent favorites = new Intent(AdvancedSearchActivity.this, FavoritesActivity.class);
                    favorites.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(favorites);
                    return true;
                case R.id.navigation_notifications:
                    Intent searchWatcher = new Intent(AdvancedSearchActivity.this, SearchWatcherActivity.class);
                    searchWatcher.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(searchWatcher);
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ImageButton cancelButton = (ImageButton) findViewById(R.id.cancel);
        cancelButton.setOnClickListener(onClickListener);

        //MultiSpinner Code
        roomTypeItems = new ArrayList<>();
        roomTypeItems.add(AccommodationHouseType.CORRIDOR.toString());
        roomTypeItems.add(AccommodationHouseType.KITCHENETTE.toString());
        roomTypeItems.add(AccommodationHouseType.COOKING_CABINET.toString());
        roomTypeItems.add(AccommodationHouseType.ONE_ROOM.toString());
        roomTypeItems.add(AccommodationHouseType.TWO_ROOMS.toString());
        roomTypeItems.add(AccommodationHouseType.THREE_ROOMS.toString());
        roomTypeItems.add(AccommodationHouseType.FOUR_ROOMS.toString());

        areasItems = new ArrayList<>();
        areasItems.add(Region.CENTER.toString());
        areasItems.add(Region.NORTH.toString());
        areasItems.add(Region.SOUTH.toString());
        areasItems.add(Region.WEST.toString());
        areasItems.add(Region.EAST.toString());

        landlordItems = new ArrayList<>();
        landlordItems.add(AccommodationHost.CHALMERS.toString());
        landlordItems.add(AccommodationHost.SGS.toString());

        roomTypeSpinner = (MultiSpinner) findViewById(R.id.roomType_spinner);
        areasSpinner = (MultiSpinner) findViewById(R.id.areas_spinner);
        landlordSpinner = (MultiSpinner) findViewById(R.id.landlord_spinner);

        roomTypeSpinner.setItems(roomTypeItems, getString(R.string.multiSpinner_roomType), new MultiSpinnerListener(AccommodationHouseType.values()));
        areasSpinner.setItems(areasItems, getString(R.string.multiSpinner_areas), new MultiSpinnerListener(Region.values()));
        landlordSpinner.setItems(landlordItems, getString(R.string.multiSpinner_landlord), new MultiSpinnerListener(AccommodationHost.values()));

        //SeekBar Code
        final TextView textViewMinPrice = (TextView) findViewById(R.id.textViewMinPrice);
        final TextView textViewMaxPrice = (TextView) findViewById(R.id.textViewMaxPrice);
        final TextView textViewMinArea = (TextView) findViewById(R.id.textViewMinArea);
        final TextView textViewMaxArea = (TextView) findViewById(R.id.textViewMaxArea);

        seekBarMinPrice = (SeekBar) findViewById(R.id.seekBarMinPrice);
        seekBarMaxPrice = (SeekBar) findViewById(R.id.seekBarMaxPrice);
        seekBarMinArea = (SeekBar) findViewById(R.id.seekBarMinArea);
        seekBarMaxArea = (SeekBar) findViewById(R.id.seekBarMaxArea);

        seekBarMinPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMinPrice));
        seekBarMaxPrice.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMaxPrice));
        seekBarMinArea.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMinArea));
        seekBarMaxArea.setOnSeekBarChangeListener(new OnSeekBarChangeListenerText(textViewMaxArea));

        seekBarMinPrice.setMax(15000);
        seekBarMaxPrice.setMax(15000);
        seekBarMaxPrice.setProgress(15000);
        seekBarMinArea.setMax(150);
        seekBarMaxArea.setMax(150);
        seekBarMaxArea.setProgress(150);

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


    public static void advancedSearchButtonPressed(View view){
        System.out.print("An advanced search was made!");
    }










}


