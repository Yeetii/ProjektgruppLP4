package se.chalmers.projektgrupplp4.studentlivinggbg;

import org.junit.Test;

import java.util.ArrayList;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchList;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Host;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.HouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;

import static org.junit.Assert.*;

/**
 * @author John
 */

public class TestSearchList {

    @Test
    public void testSearchList(){
        SearchList searchHandler = new SearchList();
        assertTrue(searchHandler instanceof SearchList);
    }

    @Test
    public void testCreateSearch(){
        Search search0 = SearchList.createSearch("");
        assertFalse(SearchList.getLastSearches().contains(search0));
        assertFalse(SearchList.getLastSearch() == search0);

        Search search1 = SearchList.createSearch("test");
        assertTrue(SearchList.getLastSearches().contains(search1));
        assertTrue(SearchList.getLastSearch() == search1);

        Search search2 = SearchList.createSearch("", false);
        assertFalse(SearchList.getLastSearches().contains(search2));
        assertFalse(SearchList.getLastSearch() == search2);

        Search search3 = SearchList.createSearch("", true);
        assertTrue(SearchList.getLastSearches().contains(search3));
        assertTrue(SearchList.getLastSearch() == search3);

        Search search4 = SearchList.createSearch("", new ArrayList<HouseType>(), new ArrayList<Host>(),
                new ArrayList<Region>(), -1, -1, -1, -1, -1, -1, false);
        assertFalse(SearchList.getLastSearches().contains(search4));
        assertFalse(SearchList.getLastSearch() == search4);


        Search search5 = SearchList.createSearch("", new ArrayList<HouseType>(), new ArrayList<Host>(),
                new ArrayList<Region>(), -1, -1, -1, -1, -1, -1, true);
        assertTrue(SearchList.getLastSearches().contains(search5));
        assertTrue(SearchList.getLastSearch() == search5);
    }

    @Test
    public void testAddToLastSearchers(){
        SearchList.createSearch("test", true);

        for(int i=0; i < 10; i++){
            SearchList.createSearch(String.valueOf(i), true);
        }

        String mainSearches = "";
        for(Search search: SearchList.getLastSearches()) {
            mainSearches = mainSearches + search.getMainSearch() + " ";
        }

        String[] stringArray = mainSearches.split(" ");
        for (String aStringArray : stringArray) {
            assertFalse(aStringArray.equals("test"));
        }

        for(int i=1; i < 10; i++){
            assertTrue(mainSearches.contains(String.valueOf(i)));
        }
    }


}
