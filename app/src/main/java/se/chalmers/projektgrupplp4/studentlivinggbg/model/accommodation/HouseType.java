package se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation;


import java.util.ArrayList;
import java.util.List;

/**
 * @author John Segerstedt
 * Used by: Accommodation, HouseType, AdvancedSearchFragmentController,
 * AdvancedSearchFragmentView, ChalmersAdapter, Search, SearchList
 * Uses: EnumType
 * Responsibility: Enum for accommodation house type.
 */

public enum HouseType implements EnumType {

    CORRIDOR, KITCHENETTE, COOKING_CABINET, ONE_ROOM, TWO_ROOMS, TWO_ROOMS_KITCHENETTE, THREE_ROOMS,
    FOUR_ROOMS, UNKNOWN;


    @Override
    public String toString() {
        switch(name()){
            case "CORRIDOR":              return "Enkelrum med gruppkök";
            case "KITCHENETTE":           return "Enkelrum med kokvrå";
            case "COOKING_CABINET":       return "Enkelrum med kokskåp";
            case "ONE_ROOM":              return "1-rum och kök";
            case "TWO_ROOMS":             return "2-rum och kök";
            case "TWO_ROOMS_KITCHENETTE": return "2-rum med kokvrå";
            case "THREE_ROOMS":           return "3-rum och kök";
            case "FOUR_ROOMS":            return "4-rum och kök";
            case "UNKNOWN":               return "Okänd";
        }
        return "ERROR";
    }

    static public HouseType parseString(String string){
        switch(string){
            case "Enkelrum med gruppkök":   return CORRIDOR;
            case "Enkelrum med kokvrå":     return KITCHENETTE;
            case "Enkelrum med kokskåp":    return COOKING_CABINET;
            case "1-rum och kök":           return ONE_ROOM;
            case "2-rum och kök":           return TWO_ROOMS;
            case "2-rum med kokvrå":        return TWO_ROOMS_KITCHENETTE;
            case "3-rum och kök":           return THREE_ROOMS;
            case "4-rum och kök":           return FOUR_ROOMS;
        }
        return null;
    }

    public static List<HouseType> parseStringList(List<String> stringList){
        List<HouseType> result = new ArrayList<>();
        for(String string: stringList){
            result.add(parseString(string));
        }
        return result;
    }
}