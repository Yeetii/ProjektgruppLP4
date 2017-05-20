package se.chalmers.projektgrupplp4.studentlivinggbg;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;

import static org.junit.Assert.*;

public class TestSearch {

    @Test
    public void testSearch(){
        ArrayList<AccommodationHouseType> houseTypeFilter = new ArrayList<>();
        houseTypeFilter.add(AccommodationHouseType.COOKING_CABINET);

        Search search1 = new Search("");
        Search search2 = new Search("", houseTypeFilter, new ArrayList<AccommodationHost>(),
                new ArrayList<Region>(), -1, -1, -1, -1, -1, -1);

        Accommodation newAccommodation = new Accommodation("", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 100, "", "", AccommodationHost.CHALMERS, Region.CENTER, "", "", false);
        Accommodation oldAccommodation = new Accommodation("", "", AccommodationHouseType.KITCHENETTE, 0, 0, 20, "", "", AccommodationHost.CHALMERS, Region.CENTER, "", "", false);

        List<Accommodation> accommodations = new ArrayList<>();
        accommodations.add(newAccommodation);
        accommodations.add(oldAccommodation);
        List<Accommodation> search2Result = search2.search(accommodations);


        assertTrue(search1.search() == Accommodation.getAccommodations());
        assertTrue(search2Result.get(0) == newAccommodation && search2Result.size() == 1);
    }

    @Test
    public void testIsEmpty(){
        Search search1 = new Search("");
        Search search2 = new Search("", new ArrayList<AccommodationHouseType>(), new ArrayList<AccommodationHost>(),
                new ArrayList<Region>(), -1, -1, -1, -1, -1, -1);
        Search search3 = new Search("", new ArrayList<AccommodationHouseType>(), new ArrayList<AccommodationHost>(),
                new ArrayList<Region>(), -1, -1, -1, -1, 10, -1);
        Search search4 = new Search("", new ArrayList<AccommodationHouseType>(), new ArrayList<AccommodationHost>(),
                new ArrayList<Region>(), -1, -1, 10, -1, -1, -1);
        Search search5 = new Search("test", new ArrayList<AccommodationHouseType>(), new ArrayList<AccommodationHost>(),
                new ArrayList<Region>(), -1, -1, -1, -1, -1, -1);

        assertTrue(search1.isEmpty());
        assertTrue(search2.isEmpty());
        assertFalse(search3.isEmpty());
        assertFalse(search4.isEmpty());
        assertFalse(search5.isEmpty());
    }
}
