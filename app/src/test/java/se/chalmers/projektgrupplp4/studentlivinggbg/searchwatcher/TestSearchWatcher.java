package se.chalmers.projektgrupplp4.studentlivinggbg.searchwatcher;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Host;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.HouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcher;

/**
 * Revised by: Erik
 */

public class TestSearchWatcher {

    @Test
    public void testCheckForMatches(){
        List<Accommodation> accommodationList1 = new ArrayList<>();
        SearchWatcher searchWatcherItem1 = new SearchWatcher("", new Search(""));
        Accommodation accommodation1 = new Accommodation("", "", HouseType.COOKING_CABINET, 0, 0, 100, "", "", Host.CHALMERS, Region.CENTER, "", "", false);

        accommodationList1.add(null);
        assertTrue(searchWatcherItem1.checkForMatches(accommodationList1) == 0);

        accommodationList1.add(accommodation1);
        assertTrue(searchWatcherItem1.checkForMatches(accommodationList1) == 1);
        assertTrue(accommodationList1.get(1).getObjectNumber().equals(searchWatcherItem1.getNewMatches().get(0)));
        assertTrue(searchWatcherItem1.getNewMatches().size()==1);

        searchWatcherItem1.setSearch(new Search(""));
        assertNotNull(searchWatcherItem1.getSearch());


        List<Accommodation> accommodationList2 = new ArrayList<>();
        SearchWatcher searchWatcherItem2 = new SearchWatcher("", new Search("gatan"));
        Accommodation accommodation2 = new Accommodation("", "fel", HouseType.COOKING_CABINET, 0, 0, 100, "fel", "fel", Host.CHALMERS, Region.CENTER, "", "", false);

        accommodationList2.add(null);
        assertTrue(searchWatcherItem2.checkForMatches(accommodationList2) == 0);

        accommodationList2.add(accommodation2);
        System.out.println(searchWatcherItem2.checkForMatches(accommodationList2));
        System.out.println(searchWatcherItem2.getSearch().search(accommodationList2));
        assertTrue(searchWatcherItem2.checkForMatches(accommodationList2) == 0);

    }

    @Test
    public void testGetters(){
        Search search = new Search("");
        SearchWatcher searchWatcherItem = new SearchWatcher("title",  search);

        assertTrue(searchWatcherItem.getTitle().equals("title"));
        assertTrue(searchWatcherItem.getSearch().getMainSearch().equals(search.getMainSearch()));
    }

}
