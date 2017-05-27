package se.chalmers.projektgrupplp4.studentlivinggbg.accommodation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.EnumHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;

import static org.junit.Assert.assertTrue;

/**
 * @author Peter Gärdenäs
 */

public class TestEnumHelper {
    @Test
    public void toStringList() {
        List<Region> regions = new ArrayList<>();
        regions.add(Region.CENTER);
        regions.add(Region.NORTH);
        List<String> regionsToStringList = EnumHelper.toStringList(regions);

        for (int i = 0; i < regions.size(); i++) {
            assertTrue(regionsToStringList.get(i).equals(regions.get(i).toString()));

        }
    }
}
