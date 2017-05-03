package se.chalmers.projektgrupplp4.studentlivinggbg.model;


public enum Region {
    //TODO Change to package private when no longer neccessary for tesing

    NORTH, EAST, SOUTH, WEST, CENTER;


    //Creating a nice toString() method
    @Override
    public String toString() {
        switch(name()){
            case "NORTH":  return "Norr";
            case "EAST":   return "Öster";
            case "SOUTH":  return "Söder";
            case "WEST":   return "Väster";
            case "CENTER": return "Centrum";

        }
        return "ERROR";
    }
}