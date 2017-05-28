package se.chalmers.projektgrupplp4.studentlivinggbg.searchwatcher;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Host;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.HouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherList;

import static org.junit.Assert.*;

/*
 * @author John Segerstedt
 */

public class TestSearchWatcherList {



    @Test
    public void testCreateSearchWatcher(){
        String name = "name";
        Search search = new Search("mainSearch");

        SearchWatcher searchWatcherItem = SearchWatcherList.createSearchWatcher(name, search);

        assertTrue(searchWatcherItem.getSearch().getMainSearch().equals("mainSearch"));
        assertTrue(searchWatcherItem.getTitle().equals("name"));
        assertTrue(SearchWatcherList.getSearchWatchers().contains(searchWatcherItem));
    }



    @Test
    public void testUpdateWatchers(){
        testCreateSearchWatcher();
        List<Accommodation> accommodationList1 = new ArrayList<>();
        Accommodation accommodation1 = new Accommodation("", "mainSearch", HouseType.COOKING_CABINET, 0, 0, 100, "", "", Host.CHALMERS, Region.CENTER, "", "", false);
        accommodationList1.add(accommodation1);
        assertTrue(SearchWatcherList.checkForMatches(accommodationList1) == 1);

    }
}
