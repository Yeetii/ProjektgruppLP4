package se.chalmers.projektgrupplp4.studentlivinggbg.accommodation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.EnumHelper;

import static org.junit.Assert.*;

/**
 * @author John
 */

public class TestAccommodationHouseType {

    @Test
    public void testToString(){
        assertTrue(AccommodationHouseType.CORRIDOR.toString().equals("Enkelrum med gruppkök"));
        assertTrue(AccommodationHouseType.KITCHENETTE.toString().equals("Enkelrum med kokvrå"));
        assertTrue(AccommodationHouseType.COOKING_CABINET.toString().equals("Enkelrum med kokskåp"));
        assertTrue(AccommodationHouseType.ONE_ROOM.toString().equals("1-rum och kök"));
        assertTrue(AccommodationHouseType.TWO_ROOMS.toString().equals("2-rum och kök"));
        assertTrue(AccommodationHouseType.TWO_ROOMS_KITCHENETTE.toString().equals("2-rum med kokvrå"));
        assertTrue(AccommodationHouseType.THREE_ROOMS.toString().equals("3-rum och kök"));
        assertTrue(AccommodationHouseType.FOUR_ROOMS.toString().equals("4-rum och kök"));
        assertTrue(AccommodationHouseType.UNKNOWN.toString().equals("Okänd"));
    }


    @Test
    public void testParseString(){
        assertTrue(AccommodationHouseType.parseString("Enkelrum med gruppkök") == AccommodationHouseType.CORRIDOR);
        assertTrue(AccommodationHouseType.parseString("Enkelrum med kokvrå") == AccommodationHouseType.KITCHENETTE);
        assertTrue(AccommodationHouseType.parseString("Enkelrum med kokskåp") == AccommodationHouseType.COOKING_CABINET);
        assertTrue(AccommodationHouseType.parseString("1-rum och kök") == AccommodationHouseType.ONE_ROOM);
        assertTrue(AccommodationHouseType.parseString("2-rum och kök") == AccommodationHouseType.TWO_ROOMS);
        assertTrue(AccommodationHouseType.parseString("2-rum med kokvrå") == AccommodationHouseType.TWO_ROOMS_KITCHENETTE);
        assertTrue(AccommodationHouseType.parseString("3-rum och kök") == AccommodationHouseType.THREE_ROOMS);
        assertTrue(AccommodationHouseType.parseString("4-rum och kök") == AccommodationHouseType.FOUR_ROOMS);
        assertTrue(AccommodationHouseType.parseString("errorString") == null);
    }

    @Test
    public void testParseStringList(){
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("Enkelrum med gruppkök");
        stringList.add("Enkelrum med kokvrå");
        stringList.add("Enkelrum med kokskåp");
        stringList.add("1-rum och kök");
        stringList.add("2-rum och kök");
        stringList.add("2-rum med kokvrå");
        stringList.add("3-rum och kök");
        stringList.add("4-rum och kök");
        stringList.add("errorString");

        List<AccommodationHouseType> newList = AccommodationHouseType.parseStringList(stringList);

        assertTrue(newList.get(0) == AccommodationHouseType.CORRIDOR);
        assertTrue(newList.get(1) == AccommodationHouseType.KITCHENETTE);
        assertTrue(newList.get(2) == AccommodationHouseType.COOKING_CABINET);
        assertTrue(newList.get(3) == AccommodationHouseType.ONE_ROOM);
        assertTrue(newList.get(4) == AccommodationHouseType.TWO_ROOMS);
        assertTrue(newList.get(5) == AccommodationHouseType.TWO_ROOMS_KITCHENETTE);
        assertTrue(newList.get(6) == AccommodationHouseType.THREE_ROOMS);
        assertTrue(newList.get(7) == AccommodationHouseType.FOUR_ROOMS);
        assertTrue(newList.get(8) == null);
    }

    @Test
    public void testToStringList(){
        ArrayList<AccommodationHouseType> newList = new ArrayList<>();
        newList.add(AccommodationHouseType.CORRIDOR);
        newList.add(AccommodationHouseType.KITCHENETTE);
        newList.add(AccommodationHouseType.COOKING_CABINET);
        newList.add(AccommodationHouseType.ONE_ROOM);
        newList.add(AccommodationHouseType.TWO_ROOMS);
        newList.add(AccommodationHouseType.TWO_ROOMS_KITCHENETTE);
        newList.add(AccommodationHouseType.THREE_ROOMS);
        newList.add(AccommodationHouseType.FOUR_ROOMS);
        newList.add(AccommodationHouseType.UNKNOWN);
        newList.add(null);

        String test = EnumHelper.toString(newList);

        assertTrue(test.equals(
                "Enkelrum med gruppkök, Enkelrum med kokvrå, Enkelrum med kokskåp, 1-rum och kök, " +
                        "2-rum och kök, 2-rum med kokvrå, 3-rum och kök, 4-rum och kök, Okänd"));
    }

}
