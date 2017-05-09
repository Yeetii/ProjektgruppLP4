package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import java.util.ArrayList;

//TODO Not used at all atm, but maybe this is more correct?
public class AdvancedSearchActivityModel {
    private ArrayList<String> roomTypeItems = new ArrayList<>();
    private ArrayList<String> areasItems = new ArrayList<>();
    private ArrayList<String> landlordItems = new ArrayList<>();

    public AdvancedSearchActivityModel(){
        //MultiSpinner Code
        roomTypeItems.add(AccommodationHouseType.CORRIDOR.toString());
        roomTypeItems.add(AccommodationHouseType.KITCHENETTE.toString());
        roomTypeItems.add(AccommodationHouseType.COOKING_CABINET.toString());
        roomTypeItems.add(AccommodationHouseType.ONE_ROOM.toString());
        roomTypeItems.add(AccommodationHouseType.TWO_ROOMS.toString());
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

    public ArrayList<String> getRoomTypeItems(){
        return roomTypeItems;
    }
    public ArrayList<String> getAreasItems() {
        return areasItems;
    }

    public ArrayList<String> getLandlordItems() {
        return landlordItems;
    }
}
