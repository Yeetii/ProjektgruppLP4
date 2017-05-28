package se.chalmers.projektgrupplp4.studentlivinggbg.searchwatcher;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;

/**
 * Revised by: Erik
 */

public class TestSearchWatcherItem {

    @Test
    public void testCheckForMatches(){
        List<Accommodation> accommodationList1 = new ArrayList<>();
        SearchWatcherItem searchWatcherItem1 = new SearchWatcherItem("", new Search(""));
        Accommodation accommodation1 = new Accommodation("", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 100, "", "", AccommodationHost.CHALMERS, Region.CENTER, "", "", false);

        accommodationList1.add(null);
        assertTrue(searchWatcherItem1.checkForMatches(accommodationList1) == 0);

        accommodationList1.add(accommodation1);
        assertTrue(searchWatcherItem1.checkForMatches(accommodationList1) == 1);
        assertTrue(accommodationList1.get(1).getObjectNumber().equals(searchWatcherItem1.getNewMatches().get(0)));
        assertTrue(searchWatcherItem1.getNewMatches().size()==1);

        searchWatcherItem1.setSearch(new Search(""));
        assertNotNull(searchWatcherItem1.getSearch());


        List<Accommodation> accommodationList2 = new ArrayList<>();
        SearchWatcherItem searchWatcherItem2 = new SearchWatcherItem("", new Search("gatan"));
        Accommodation accommodation2 = new Accommodation("", "fel", AccommodationHouseType.COOKING_CABINET, 0, 0, 100, "fel", "fel", AccommodationHost.CHALMERS, Region.CENTER, "", "", false);

        accommodationList2.add(null);
        assertTrue(searchWatcherItem2.checkForMatches(accommodationList2) == 0);

        accommodationList2.add(accommodation2);
        assertTrue(searchWatcherItem2.checkForMatches(accommodationList2) == 0);

    }

    @Test
    public void testGetters(){
        Search search = new Search("");
        SearchWatcherItem searchWatcherItem = new SearchWatcherItem("title",  search);

        assertTrue(searchWatcherItem.getTitle().equals("title"));
        assertTrue(searchWatcherItem.getSearch().getMainSearch().equals(search.getMainSearch()));
    }

}
