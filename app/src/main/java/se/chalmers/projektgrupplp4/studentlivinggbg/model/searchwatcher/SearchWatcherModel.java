package se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;

/**
 * Created by Erik on 2017-05-12.
 */

public class SearchWatcherModel {
    private static List<SearchWatcherItem> searchWatcherItems = new ArrayList<>();

    //TODO move to tests
    private static void initializeTestSearchWatchers() {

        ArrayList<AccommodationHouseType> testHouseType = new ArrayList<>();
        testHouseType.add(AccommodationHouseType.COOKING_CABINET);

        ArrayList<AccommodationHouseType> testHouseType2 = new ArrayList<>();
        testHouseType2.add(AccommodationHouseType.FOUR_ROOMS);

        ArrayList<AccommodationHost> testHost = new ArrayList<>();
        testHost.add(AccommodationHost.SGS);

        ArrayList<Region> testRegion = new ArrayList<>();
        testRegion.add(Region.NORTH);

        Search search1 = new Search("Bra");

        Search search2 = new Search("Bra", "",
                testHouseType, testHost, testRegion,
                700, 9998, 100, 450, 9999,
                "13-12-17", "24-12-17");

        Search search3 = new Search("Omg", "Gibraltargatan",
                testHouseType2, testHost, testRegion,
                700, -1, 100, -1, 9999,
                "13-12-17", "24-12-17");


        searchWatcherItems.add(new SearchWatcherItem("Gamla boendet", search1));
        searchWatcherItems.add(new SearchWatcherItem("Nära masters", search2));
        searchWatcherItems.add(new SearchWatcherItem("Lättast att få", search3));
    }

    public static SearchWatcherItem createSearchWatcher(String name, Search search){
        SearchWatcherItem sWItem = new SearchWatcherItem(name, search);
        searchWatcherItems.add(sWItem);
        System.out.println("Creating " + name + " " + search + " " + searchWatcherItems.size());
        return sWItem;
    }

    public static List<SearchWatcherItem> getSearchWatcherItems(){
        return searchWatcherItems;}

//    public static void updateWatchers(){
//        List<Accommodation> matches = null;
//        for (SearchWatcherItem sWItem : searchWatcherItems){
//            matches.addAll(SearchHandler.search(sWItem.getSearch()));
//        }
//    }

    public static int updateWatchers(List<Accommodation> newAccommodations){
        int matches = 0;
        for (SearchWatcherItem sWItem : searchWatcherItems){
            matches += sWItem.checkForMatches(newAccommodations);
        }
        return matches;
    }
}

