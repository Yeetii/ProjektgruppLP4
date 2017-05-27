package se.chalmers.projektgrupplp4.studentlivinggbg;


import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;


/**
 * @author John
 */

public class TestSearch {

    @Test
    public void testSearch(){
        ArrayList<AccommodationHouseType> houseTypeFilter = new ArrayList<>();
        houseTypeFilter.add(AccommodationHouseType.COOKING_CABINET);

        Search search1 = new Search("");
        Search search2 = new Search("", houseTypeFilter, new ArrayList<AccommodationHost>(),
                new ArrayList<Region>(), -1, -1, -1, -1, 6, 6);

        Date todayDate = new Date();
        String todayString = todayDate.toString();

        String notToday = todayString.substring(8,10);
        notToday = notToday+"-"+parseDateMonth(todayString, true);
        notToday = notToday+"-"+todayString.substring(25,29);

        String today = todayString.substring(8,10);
        today = today+"-"+parseDateMonth(todayString, false);
        today = today+"-"+todayString.substring(25,29);

        Accommodation newAccommodation = new Accommodation("", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 100, "", "", AccommodationHost.CHALMERS, Region.CENTER, today, today, false);
        Accommodation middleAccommodation = new Accommodation("", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 100, "", "", AccommodationHost.CHALMERS, Region.CENTER, notToday, notToday, false);
        Accommodation oldAccommodation = new Accommodation("test", "", AccommodationHouseType.KITCHENETTE, 0, 0, 20, "", "", AccommodationHost.CHALMERS, Region.CENTER, notToday, notToday, false);

        List<Accommodation> accommodations = new ArrayList<>();
        accommodations.add(newAccommodation);
        accommodations.add(middleAccommodation);
        accommodations.add(oldAccommodation);
        List<Accommodation> search2Result = search2.search(accommodations);


        List<Accommodation> allAccommodations = search1.search();
        List<Accommodation> search1Result = Accommodation.getAccommodations();


        assertTrue(allAccommodations.size() == search1Result.size());
        assertTrue(search2Result.get(0) == newAccommodation && search2Result.size() == 1);

    }

    private String parseDateMonth(String string, boolean addMonth) {
        string = string.substring(4,7);

        switch(string){
            case "Jan": string = "01";
                break;
            case "Feb": string = "02";
                break;
            case "Mar": string = "03";
                break;
            case "Apr": string = "04";
                break;
            case "May": string = "05";
                break;
            case "Jun": string = "06";
                break;
            case "Jul": string = "07";
                break;
            case "Aug": string = "08";
                break;
            case "Sep": string = "09";
                break;
            case "Oct": string = "10";
                break;
            case "Nov": string = "11";
                break;
            case "Dec": string = "12";
                break;
        }

        if(addMonth){
        String result = String.valueOf(Integer.valueOf(string) -1);
        if(result.length() == 1){
            result = "0"+result;
        }
        return result;}
        else{
            return string;
        }
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
                new ArrayList<Region>(), -1, -1, -1, -1, 6, 6);

        assertTrue(search1.isEmpty());
        assertTrue(search2.isEmpty());
        assertFalse(search3.isEmpty());
        assertFalse(search4.isEmpty());
        assertFalse(search5.isEmpty());
    }
}
