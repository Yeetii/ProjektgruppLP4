package se.chalmers.projektgrupplp4.studentlivinggbg.accommodation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.EnumHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.HouseType;

import static org.junit.Assert.*;

/**
 * @author John
 */

public class TestHouseType {

    @Test
    public void testToString(){
        assertTrue(HouseType.CORRIDOR.toString().equals("Enkelrum med gruppkök"));
        assertTrue(HouseType.KITCHENETTE.toString().equals("Enkelrum med kokvrå"));
        assertTrue(HouseType.COOKING_CABINET.toString().equals("Enkelrum med kokskåp"));
        assertTrue(HouseType.ONE_ROOM.toString().equals("1-rum och kök"));
        assertTrue(HouseType.TWO_ROOMS.toString().equals("2-rum och kök"));
        assertTrue(HouseType.TWO_ROOMS_KITCHENETTE.toString().equals("2-rum med kokvrå"));
        assertTrue(HouseType.THREE_ROOMS.toString().equals("3-rum och kök"));
        assertTrue(HouseType.FOUR_ROOMS.toString().equals("4-rum och kök"));
        assertTrue(HouseType.UNKNOWN.toString().equals("Okänd"));
    }


    @Test
    public void testParseString(){
        assertTrue(HouseType.parseString("Enkelrum med gruppkök") == HouseType.CORRIDOR);
        assertTrue(HouseType.parseString("Enkelrum med kokvrå") == HouseType.KITCHENETTE);
        assertTrue(HouseType.parseString("Enkelrum med kokskåp") == HouseType.COOKING_CABINET);
        assertTrue(HouseType.parseString("1-rum och kök") == HouseType.ONE_ROOM);
        assertTrue(HouseType.parseString("2-rum och kök") == HouseType.TWO_ROOMS);
        assertTrue(HouseType.parseString("2-rum med kokvrå") == HouseType.TWO_ROOMS_KITCHENETTE);
        assertTrue(HouseType.parseString("3-rum och kök") == HouseType.THREE_ROOMS);
        assertTrue(HouseType.parseString("4-rum och kök") == HouseType.FOUR_ROOMS);
        assertTrue(HouseType.parseString("errorString") == null);
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

        List<HouseType> newList = HouseType.parseStringList(stringList);

        assertTrue(newList.get(0) == HouseType.CORRIDOR);
        assertTrue(newList.get(1) == HouseType.KITCHENETTE);
        assertTrue(newList.get(2) == HouseType.COOKING_CABINET);
        assertTrue(newList.get(3) == HouseType.ONE_ROOM);
        assertTrue(newList.get(4) == HouseType.TWO_ROOMS);
        assertTrue(newList.get(5) == HouseType.TWO_ROOMS_KITCHENETTE);
        assertTrue(newList.get(6) == HouseType.THREE_ROOMS);
        assertTrue(newList.get(7) == HouseType.FOUR_ROOMS);
        assertTrue(newList.get(8) == null);
    }

    @Test
    public void testToStringList(){
        ArrayList<HouseType> newList = new ArrayList<>();
        newList.add(HouseType.CORRIDOR);
        newList.add(HouseType.KITCHENETTE);
        newList.add(HouseType.COOKING_CABINET);
        newList.add(HouseType.ONE_ROOM);
        newList.add(HouseType.TWO_ROOMS);
        newList.add(HouseType.TWO_ROOMS_KITCHENETTE);
        newList.add(HouseType.THREE_ROOMS);
        newList.add(HouseType.FOUR_ROOMS);
        newList.add(HouseType.UNKNOWN);
        newList.add(null);

        String test = EnumHelper.toString(newList);

        assertTrue(test.equals(
                "Enkelrum med gruppkök, Enkelrum med kokvrå, Enkelrum med kokskåp, 1-rum och kök, " +
                        "2-rum och kök, 2-rum med kokvrå, 3-rum och kök, 4-rum och kök, Okänd"));
    }

}
