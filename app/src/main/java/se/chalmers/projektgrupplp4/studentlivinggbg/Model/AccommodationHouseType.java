package se.chalmers.projektgrupplp4.studentlivinggbg.Model;


public enum AccommodationHouseType {
    //TODO Change to package private when no longer neccessary for tesing

    CORRIDOR, KITCHENETTE, COOKING_CABINET, ONE_ROOM, TWO_ROOMS, THREE_ROOMS, FOUR_ROOMS;


    //Creating a nice toString() method
    @Override
    public String toString() {
        switch(name()){
            case "CORRIDOR": return "Enkelrum med gruppkök";
            case "KITCHENETTE": return "Enkelrum med kokvrå";
            case "COOKING_CABINET": return "Enkelrum med kokskåp";
            case "ONE_ROOM": return "1-rum och kök";
            case "TWO_ROOMS": return "2-rum och kök";
            case "THREE_ROOMS": return "3-rum och kök";
            case "FOUR_ROOMS": return "4-rum och kök";
        }
        return "ERROR";
    }
}