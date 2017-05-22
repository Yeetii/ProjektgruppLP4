package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;

public class SearchHandler {

    static private List<Search> lastSearches = new ArrayList<>();

    public SearchHandler () {}

    public static Search createSearch(String mainSearch){
        Search result = new Search(mainSearch);
        if (!(mainSearch == null || mainSearch.isEmpty() || mainSearch.equals("") || mainSearch.length() <= 0)) {
            addToLastSearches(result);
        }
        return result;
    }


    public static Search createSearch(String mainSearch, boolean AddToList){
        Search result = new Search(mainSearch);
        if(AddToList) {addToLastSearches(result);}
        return result;
    }

    public static Search createSearch(String mainSearch,
                                      ArrayList<AccommodationHouseType> possibleAccomodationHouseTypes,
                                      ArrayList<AccommodationHost> possibleAccomodationHosts,
                                      ArrayList<Region> possibleRegions,
                                      int minPrice, int maxPrice, int minArea, int maxArea,
                                      int daysUpploaded, int daysLeft, boolean addToList){

        Search result = new Search(mainSearch,
                possibleAccomodationHouseTypes,
                possibleAccomodationHosts,
                possibleRegions,
                minPrice, maxPrice, minArea, maxArea,
                daysUpploaded, daysLeft);

        if(addToList) {
            addToLastSearches(result);
        }

        return result;
    }

    public static void addToLastSearches(Search result) {
        if(lastSearches.size() >= 10){
            lastSearches.remove(lastSearches.size()-1);
        }
        lastSearches.add(0, result);
    }

    public static Search getLastSearch(){
        try{
            return lastSearches.get(0);}
        catch(IndexOutOfBoundsException e){
            return new Search("");}}

    public static List<Search> getLastSearches(){
        return lastSearches;
    }
}
