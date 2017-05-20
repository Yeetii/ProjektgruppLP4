package se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation;


import java.util.ArrayList;

public enum Region {
    //TODO Change to package private when no longer neccessary for tesing

    NORTH, EAST, WEST, CENTER;


    //Creating a nice toString() method
    @Override
    public String toString() {
        switch(name()){
            case "NORTH":  return "Norr";
            case "EAST":   return "Öster";
            case "WEST":   return "Väster";
            case "CENTER": return "Centrum";

        }
        return "ERROR";
    }

    static public Region parseString(String string){
        switch(string){
            case "Norr":                return NORTH;
            case "Öster":               return EAST;
            case "Väster":              return WEST;
            case "Centrum":             return CENTER;
        }
        return null;
    }

    public static ArrayList<Region> parseStringList(ArrayList<String> stringList){
        ArrayList<Region> result = new ArrayList<>();
        for(String string: stringList){
            result.add(parseString(string));
        }
        return result;
    }

    public static String toStringList(ArrayList<Region> regionArray){
        try{
            String result = "";
            for(Region region: regionArray){
                if(region != null){
                    result = result + region.toString() + ", ";
                }}
            if(!result.equals("")){return result.substring(0, result.length() - 2);}
            return result;
        }
        catch(Exception e){
            return "";}
    }
}