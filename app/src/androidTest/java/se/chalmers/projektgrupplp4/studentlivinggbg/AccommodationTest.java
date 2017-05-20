package se.chalmers.projektgrupplp4.studentlivinggbg;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;

import static org.junit.Assert.*;

public class AccommodationTest {

    @Test
    public void testAddToAccommodations(){
        Accommodation newAccommodationAdd = new Accommodation("", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 0, "", "", AccommodationHost.CHALMERS, Region.CENTER, "", "", true);
        Accommodation newAccommodationNotAdd = new Accommodation("", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 0, "", "", AccommodationHost.CHALMERS, Region.CENTER, "", "", false);
        List<Accommodation> allAccommodations = Accommodation.getAccommodations();

        assertTrue(allAccommodations.contains(newAccommodationAdd));
        assertFalse(allAccommodations.contains(newAccommodationNotAdd));
    }

    @Test
    public void testGetFavorites(){
        List<Accommodation> allAccommodations = Accommodation.getAccommodations();
        List<Accommodation> favorites = Accommodation.getFavorites();

        for(Accommodation accommodation: allAccommodations){

            if (favorites.contains(accommodation)) {
                //All "favorites" are really favorites
                assertTrue(accommodation.getFavorite());
            }else{
                //All that are not "favorites" are really not favorites
                assertFalse(accommodation.getFavorite());
            }
        }
    }

    @Test
    public void testUpdate(){
        Accommodation newAccommodation = new Accommodation("", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 100, "", "", AccommodationHost.CHALMERS, Region.CENTER, "", "", false);
        Accommodation oldAccommodation = new Accommodation("", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 20, "", "", AccommodationHost.CHALMERS, Region.CENTER, "", "", false);
        assertTrue(Integer.valueOf(oldAccommodation.getSearchers()) == 20);
        oldAccommodation.update(newAccommodation);
        assertTrue(Integer.valueOf(oldAccommodation.getSearchers()) == 100);
    }

    @Test
    public void testTransferFavoriteStatus(){
        List<Accommodation> newAccommodations = new ArrayList<>();
        List<Accommodation> previousAccommodations = new ArrayList<>();

        Accommodation accommodation1 = new Accommodation("1", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 100, "", "", AccommodationHost.CHALMERS, Region.CENTER, "", "", false);
        Accommodation accommodation2 = new Accommodation("2", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 100, "", "", AccommodationHost.CHALMERS, Region.CENTER, "", "", false);
        Accommodation accommodation3 = new Accommodation("1", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 100, "", "", AccommodationHost.CHALMERS, Region.CENTER, "", "", false);
        Accommodation accommodation4 = new Accommodation("2", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 100, "", "", AccommodationHost.CHALMERS, Region.CENTER, "", "", false);

        accommodation1.setFavorite(false);
        accommodation2.setFavorite(false);
        accommodation3.setFavorite(true);
        accommodation4.setFavorite(true);

        newAccommodations.add(accommodation1);
        newAccommodations.add(accommodation2);

        previousAccommodations.add(accommodation3);
        previousAccommodations.add(accommodation4);

        assertFalse(newAccommodations.get(0).getFavorite());
        assertFalse(newAccommodations.get(1).getFavorite());
        assertTrue(previousAccommodations.get(0).getFavorite());
        assertTrue(previousAccommodations.get(1).getFavorite());

        Accommodation.transferFavoriteStatus(previousAccommodations, newAccommodations);

        assertTrue(newAccommodations.get(0).getFavorite());
        assertTrue(newAccommodations.get(1).getFavorite());
        assertTrue(previousAccommodations.get(0).getFavorite());
        assertTrue(previousAccommodations.get(1).getFavorite());
    }

}
