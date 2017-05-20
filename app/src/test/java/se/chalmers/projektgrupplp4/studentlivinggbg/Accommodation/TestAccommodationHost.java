package se.chalmers.projektgrupplp4.studentlivinggbg.Accommodation;

import org.junit.Test;

import java.util.ArrayList;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;

import static org.junit.Assert.*;


public class TestAccommodationHost {

    @Test
    public void testToString(){
        assertTrue(AccommodationHost.SGS.toString().equals("SGS Studentbostäder"));
        assertTrue(AccommodationHost.CHALMERS.toString().equals("Chalmers Studentbostäder"));
    }

    @Test
    public void testToStringShort(){
        assertTrue(AccommodationHost.SGS.toStringShort().equals("SGS"));
        assertTrue(AccommodationHost.CHALMERS.toStringShort().equals("Chalmers"));
    }

    @Test
    public void testParseString(){
        assertTrue(AccommodationHost.parseString("SGS Studentbostäder") == AccommodationHost.SGS);
        assertTrue(AccommodationHost.parseString("Chalmers Studentbostäder") == AccommodationHost.CHALMERS);
        assertTrue(AccommodationHost.parseString("errorString") == null);
    }

    @Test
    public void testParseStringList(){
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("SGS Studentbostäder");
        stringList.add("Chalmers Studentbostäder");
        stringList.add("errorString");

        ArrayList<AccommodationHost> newList = AccommodationHost.parseStringList(stringList);

        assertTrue(newList.get(0) == AccommodationHost.SGS);
        assertTrue(newList.get(1) == AccommodationHost.CHALMERS);
        assertTrue(newList.get(2) == null);
    }

    @Test
    public void testToStringList(){
        ArrayList<AccommodationHost> newList = new ArrayList<>();
        newList.add(AccommodationHost.SGS);
        newList.add(AccommodationHost.CHALMERS);
        newList.add(null);

        assertTrue(AccommodationHost.toStringList(newList).equals("SGS Studentbostäder, Chalmers Studentbostäder"));
    }

    @Test
    public void testToStringListShort(){
        ArrayList<AccommodationHost> newList = new ArrayList<>();
        newList.add(AccommodationHost.SGS);
        newList.add(AccommodationHost.CHALMERS);
        newList.add(null);

        assertTrue(AccommodationHost.toStringListShort(newList).equals("SGS, Chalmers"));
    }

}
