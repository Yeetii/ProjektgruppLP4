package se.chalmers.projektgrupplp4.studentlivinggbg.accommodation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.EnumHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Host;

import static org.junit.Assert.*;

/**
 * @author John
 */

public class TestHost {

    @Test
    public void testToString(){
        assertTrue(Host.SGS.toString().equals("SGS Studentbostäder"));
        assertTrue(Host.CHALMERS.toString().equals("Chalmers Studentbostäder"));
    }

    @Test
    public void testToStringShort(){
        assertTrue(Host.SGS.toStringShort().equals("SGS"));
        assertTrue(Host.CHALMERS.toStringShort().equals("Chalmers"));
    }

    @Test
    public void testParseString(){
        assertTrue(Host.parseString("SGS Studentbostäder") == Host.SGS);
        assertTrue(Host.parseString("Chalmers Studentbostäder") == Host.CHALMERS);
        assertTrue(Host.parseString("errorString") == null);
    }

    @Test
    public void testParseStringList(){
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("SGS Studentbostäder");
        stringList.add("Chalmers Studentbostäder");
        stringList.add("errorString");

        List<Host> newList = Host.parseStringList(stringList);

        assertTrue(newList.get(0) == Host.SGS);
        assertTrue(newList.get(1) == Host.CHALMERS);
        assertTrue(newList.get(2) == null);
    }

    @Test
    public void testToStringList(){
        ArrayList<Host> newList = new ArrayList<>();
        newList.add(Host.SGS);
        newList.add(Host.CHALMERS);
        newList.add(null);

        assertTrue(EnumHelper.toString(newList).equals("SGS Studentbostäder, Chalmers Studentbostäder"));
    }

    @Test
    public void testToStringListShort(){
        ArrayList<Host> newList = new ArrayList<>();
        newList.add(Host.SGS);
        newList.add(Host.CHALMERS);
        newList.add(null);

        assertTrue(Host.toStringShort(newList).equals("SGS, Chalmers"));
    }

}
