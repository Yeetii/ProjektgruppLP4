package se.chalmers.projektgrupplp4.studentlivinggbg.searchwatcher;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;

import static org.junit.Assert.*;

public class TestSearchWatcherModel {

    @Test
    public void testCreateSearchWatcher(){
        String name = "name";
        Search search = new Search("mainSearch");

        SearchWatcherItem searchWatcherItem = SearchWatcherModel.createSearchWatcher(name, search);

        assertTrue(searchWatcherItem.getSearch().getMainSearch().equals("mainSearch"));
        assertTrue(searchWatcherItem.getTitle().equals("name"));
        assertTrue(SearchWatcherModel.getSearchWatcherItems().contains(searchWatcherItem));
    }



    @Test
    public void testUpdateWatchers(){

        List<Accommodation> accommodationList1 = new ArrayList<>();
        Accommodation accommodation1 = new Accommodation("", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 100, "", "", AccommodationHost.CHALMERS, Region.CENTER, "", "", false);
        accommodationList1.add(accommodation1);
        assertTrue(SearchWatcherModel.updateWatchers(accommodationList1) == 0);

    }
}
