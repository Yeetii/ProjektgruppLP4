package se.chalmers.projektgrupplp4.studentlivinggbg;

import org.junit.Test;

import java.util.ArrayList;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;

import static org.junit.Assert.*;

/**
 * @author John
 */

public class TestSearchHandler {

    @Test
    public void testSearchHandler(){
        SearchHandler searchHandler = new SearchHandler();
        assertTrue(searchHandler instanceof SearchHandler);
    }

    @Test
    public void testCreateSearch(){
        Search search0 = SearchHandler.createSearch("");
        assertFalse(SearchHandler.getLastSearches().contains(search0));
        assertFalse(SearchHandler.getLastSearch() == search0);

        Search search1 = SearchHandler.createSearch("test");
        assertTrue(SearchHandler.getLastSearches().contains(search1));
        assertTrue(SearchHandler.getLastSearch() == search1);

        Search search2 = SearchHandler.createSearch("", false);
        assertFalse(SearchHandler.getLastSearches().contains(search2));
        assertFalse(SearchHandler.getLastSearch() == search2);

        Search search3 = SearchHandler.createSearch("", true);
        assertTrue(SearchHandler.getLastSearches().contains(search3));
        assertTrue(SearchHandler.getLastSearch() == search3);

        Search search4 = SearchHandler.createSearch("", new ArrayList<AccommodationHouseType>(), new ArrayList<AccommodationHost>(),
                new ArrayList<Region>(), -1, -1, -1, -1, -1, -1, false);
        assertFalse(SearchHandler.getLastSearches().contains(search4));
        assertFalse(SearchHandler.getLastSearch() == search4);


        Search search5 = SearchHandler.createSearch("", new ArrayList<AccommodationHouseType>(), new ArrayList<AccommodationHost>(),
                new ArrayList<Region>(), -1, -1, -1, -1, -1, -1, true);
        assertTrue(SearchHandler.getLastSearches().contains(search5));
        assertTrue(SearchHandler.getLastSearch() == search5);
    }

    @Test
    public void testAddToLastSearchers(){
        SearchHandler.createSearch("test", true);

        for(int i=0; i < 10; i++){
            SearchHandler.createSearch(String.valueOf(i), true);
        }

        String mainSearches = "";
        for(Search search: SearchHandler.getLastSearches()) {
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
