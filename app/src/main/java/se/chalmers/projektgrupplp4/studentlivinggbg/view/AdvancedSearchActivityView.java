package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import java.util.ArrayList;
import se.chalmers.projektgrupplp4.studentlivinggbg.MultiSpinner;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;

/**
 * Created by Erik on 2017-05-05.
 */

public class AdvancedSearchActivityView {
    private Activity activity;

    //TODO does this follow MVC?
    private ArrayList<String> roomTypeItems = new ArrayList<>();
    private ArrayList<String> areasItems = new ArrayList<>();
    private ArrayList<String> landlordItems = new ArrayList<>();

    public AdvancedSearchActivityView(Activity activity){
        this.activity = activity;

        this.activity.setContentView(R.layout.activity_advanced_search);

        initSeekBars();
        initMultiSpinners();
    }

    private void initMultiSpinners() {
        MultiSpinner roomTypeSpinner;
        MultiSpinner areasSpinner;
        MultiSpinner landlordSpinner;

        roomTypeSpinner = (MultiSpinner) activity.findViewById(R.id.roomType_spinner);
        areasSpinner = (MultiSpinner) activity.findViewById(R.id.areas_spinner);
        landlordSpinner = (MultiSpinner) activity.findViewById(R.id.landlord_spinner);
        fillLists();

//        TODO Should this get its items from a static methods?
        roomTypeSpinner.setItems(roomTypeItems, activity.getString(R.string.multiSpinner_roomType));
        areasSpinner.setItems(areasItems, activity.getString(R.string.multiSpinner_areas));
        landlordSpinner.setItems(landlordItems, activity.getString(R.string.multiSpinner_landlord));
    }

    private void initSeekBars(){
        //The code below has been removed since it is basically the same as in the controller

        /*
        SeekBar seekBarMinPrice = (SeekBar) activity.findViewById(R.id.seekBarMinPrice);
        SeekBar seekBarMaxPrice = (SeekBar) activity.findViewById(R.id.seekBarMaxPrice);
        SeekBar seekBarMinArea = (SeekBar) activity.findViewById(R.id.seekBarMinArea);
        SeekBar seekBarMaxArea = (SeekBar) activity.findViewById(R.id.seekBarMaxArea);

        TextView textViewMaxPrice = (TextView) activity.findViewById(R.id.textViewMaxPrice);
        TextView textViewMaxArea = (TextView) activity.findViewById(R.id.textViewMaxArea);

        seekBarMinPrice.setMax(15000);
        seekBarMaxPrice.setMax(15000);
        textViewMaxPrice.setText("15000");
        seekBarMinArea.setMax(150);
        seekBarMaxArea.setMax(150);
        textViewMaxArea.setText("150");
        */
    }


    private void fillLists(){
        //TODO: This sohuld be done dynamically
        roomTypeItems.add(AccommodationHouseType.CORRIDOR.toString());
        roomTypeItems.add(AccommodationHouseType.KITCHENETTE.toString());
        roomTypeItems.add(AccommodationHouseType.COOKING_CABINET.toString());
        roomTypeItems.add(AccommodationHouseType.ONE_ROOM.toString());
        roomTypeItems.add(AccommodationHouseType.TWO_ROOMS.toString());
        roomTypeItems.add(AccommodationHouseType.TWO_ROOMS_KITCHENETTE.toString());
        roomTypeItems.add(AccommodationHouseType.THREE_ROOMS.toString());
        roomTypeItems.add(AccommodationHouseType.FOUR_ROOMS.toString());

        areasItems.add(Region.CENTER.toString());
        areasItems.add(Region.NORTH.toString());
        areasItems.add(Region.SOUTH.toString());
        areasItems.add(Region.WEST.toString());
        areasItems.add(Region.EAST.toString());

        landlordItems.add(AccommodationHost.CHALMERS.toString());
        landlordItems.add(AccommodationHost.SGS.toString());
    }
}
