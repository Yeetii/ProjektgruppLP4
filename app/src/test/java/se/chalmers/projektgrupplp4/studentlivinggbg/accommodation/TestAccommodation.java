package se.chalmers.projektgrupplp4.studentlivinggbg.accommodation;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Host;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.HouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;

import static org.junit.Assert.*;

/**
 * @author John
 */

public class TestAccommodation {

    @Test
    public void testSettersAndGetters(){

        String objectNumber = "12";
        String address = "testgatan1";
        HouseType accommodationHouseType = HouseType.COOKING_CABINET;
        Region region = Region.CENTER;
        int price = 10000;
        double area = 100.1;
        int searchers = 10;
        String thumbnail = "test";
        List<Integer> images = new ArrayList<>();
        images.add(1);
        String description = "haha";
        Host accommodationHost = Host.CHALMERS;
        boolean isFavorite = false;
        String upploadDate = "11-11-12";
        String lastApplyDate = "12-11-12";

        Accommodation accommodation = new Accommodation(objectNumber,address,accommodationHouseType,price,area,searchers,thumbnail,description,accommodationHost, region,upploadDate, lastApplyDate, false);

        assertTrue(accommodation.getFurnitured().equals("nej"));
        assertTrue(accommodation.getAddress().equals(address));
        assertTrue(accommodation.getPrice().equals(Integer.toString(price)));
        assertTrue(accommodation.getArea().equals(Double.toString(area)));
        assertTrue(accommodation.getSearchers().equals(Integer.toString(searchers)));
        accommodation.setSearchers(9);
        assertTrue(accommodation.getSearchers().equals(Integer.toString(9)));
        assertTrue(accommodation.getImagePath().equals(thumbnail.substring(thumbnail.indexOf("file=") + "file=".length())));
        assertTrue(accommodation.getThumbnail().equals(thumbnail));
        assertTrue(accommodation.getDescription().equals(description));
        assertTrue(accommodation.getHost().equals(accommodationHost.toString()));
        assertTrue(accommodation.getRegion().equals(region.toString()));


    }

    @Test
    public void testAddToAccommodations(){
        Accommodation newAccommodationAdd = new Accommodation("", "", HouseType.COOKING_CABINET, 0, 0, 0, "", "", Host.CHALMERS, Region.CENTER, "", "", true);
        Accommodation newAccommodationNotAdd = new Accommodation("", "", HouseType.COOKING_CABINET, 0, 0, 0, "", "", Host.CHALMERS, Region.CENTER, "", "", false);
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
        Accommodation newAccommodation = new Accommodation("", "", HouseType.COOKING_CABINET, 0, 0, 100, "", "", Host.CHALMERS, Region.CENTER, "", "", false);
        Accommodation oldAccommodation = new Accommodation("", "", HouseType.COOKING_CABINET, 0, 0, 20, "", "", Host.CHALMERS, Region.CENTER, "", "", false);
        assertTrue(Integer.valueOf(oldAccommodation.getSearchers()) == 20);
        oldAccommodation.update(newAccommodation);
        assertTrue(Integer.valueOf(oldAccommodation.getSearchers()) == 100);
    }

    @Test
    public void testSetNewAccommodationList(){
        Accommodation.getAccommodations().clear();
        Accommodation newAccommodation = new Accommodation("", "", HouseType.COOKING_CABINET, 0, 0, 0, "", "", Host.CHALMERS, Region.CENTER, "", "", false);
        List<Accommodation> accommodationList = new ArrayList<>();
        accommodationList.add(newAccommodation);

        assertFalse(Accommodation.getAccommodations().contains(newAccommodation));
        Accommodation.setNewAccommodationList(accommodationList);
        assertTrue(Accommodation.getAccommodations().contains(newAccommodation));
    }

    @Test
    public void testTransferFavoriteStatus(){
        List<Accommodation> newAccommodations = new ArrayList<>();
        List<Accommodation> previousAccommodations = new ArrayList<>();

        Accommodation accommodation1 = new Accommodation("1", "", HouseType.COOKING_CABINET, 0, 0, 100, "", "", Host.CHALMERS, Region.CENTER, "", "", false);
        Accommodation accommodation2 = new Accommodation("2", "", HouseType.COOKING_CABINET, 0, 0, 100, "", "", Host.CHALMERS, Region.CENTER, "", "", false);
        Accommodation accommodation3 = new Accommodation("1", "", HouseType.COOKING_CABINET, 0, 0, 100, "", "", Host.CHALMERS, Region.CENTER, "", "", false);
        Accommodation accommodation4 = new Accommodation("2", "", HouseType.COOKING_CABINET, 0, 0, 100, "", "", Host.CHALMERS, Region.CENTER, "", "", false);

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
