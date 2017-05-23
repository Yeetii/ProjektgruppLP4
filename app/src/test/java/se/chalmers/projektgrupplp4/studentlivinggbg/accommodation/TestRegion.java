package se.chalmers.projektgrupplp4.studentlivinggbg.accommodation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;

import static org.junit.Assert.*;


public class TestRegion {

    @Test
    public void testToString(){
        assertTrue(Region.NORTH.toString().equals("Norr"));
        assertTrue(Region.EAST.toString().equals("Öster"));
        assertTrue(Region.WEST.toString().equals("Väster"));
        assertTrue(Region.CENTER.toString().equals("Centrum"));
    }


    @Test
    public void testParseString(){
        assertTrue(Region.parseString("Norr") == Region.NORTH);
        assertTrue(Region.parseString("Öster") == Region.EAST);
        assertTrue(Region.parseString("Väster") == Region.WEST);
        assertTrue(Region.parseString("Centrum") == Region.CENTER);
        assertTrue(Region.parseString("errorString") == null);
    }

    @Test
    public void testParseStringList(){
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("Norr");
        stringList.add("Öster");
        stringList.add("Väster");
        stringList.add("Centrum");
        stringList.add("errorString");

        List<Region> newList = Region.parseStringList(stringList);

        assertTrue(newList.get(0) == Region.NORTH);
        assertTrue(newList.get(1) == Region.EAST);
        assertTrue(newList.get(2) == Region.WEST);
        assertTrue(newList.get(3) == Region.CENTER);
        assertTrue(newList.get(4) == null);
    }

    @Test
    public void testToStringList(){
        ArrayList<Region> newList = new ArrayList<>();
        newList.add(Region.NORTH);
        newList.add(Region.EAST);
        newList.add(Region.WEST);
        newList.add(Region.CENTER);
        newList.add(null);

        assertTrue(Region.toStringList(newList).equals("Norr, Öster, Väster, Centrum"));
    }

}
