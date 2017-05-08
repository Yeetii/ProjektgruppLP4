package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import java.util.ArrayList;
import java.util.List;

public class SearchHandler {

    static private List<Search> lastSearches = new ArrayList<>();

    public SearchHandler () {}

    public static Search createSearch(String mainSearch){
        Search result = new Search(mainSearch);
        if(mainSearch == null || mainSearch.isEmpty() || mainSearch.equals("") || mainSearch.length() <= 0) {}
        else{
        addToLastSearches(result);}
        return result;
    }

    public static Search createSearch(String mainSearch, String address,
                               ArrayList<AccommodationHouseType> possibleAccomodationHouseTypes,
                               ArrayList<AccommodationHost> possibleAccomodationHosts,
                               ArrayList<Region> possibleRegions,
                               int minPrice, int maxPrice, double minArea, double maxArea, int maxSearchers,
                               String upploadDate, String lastApplyDate){

        Search result = new Search(mainSearch, address,
                possibleAccomodationHouseTypes,
                possibleAccomodationHosts,
                possibleRegions,
                minPrice, maxPrice, minArea, maxArea, maxSearchers,
                upploadDate, lastApplyDate);

        addToLastSearches(result);

        return result;
    }

    static void addToLastSearches(Search result) {
        if(lastSearches.size() >= 10){
            lastSearches.remove(lastSearches.size()-1);
        }
        lastSearches.add(0, result);
    }

    private List<Search> getLastSearches () {
        return lastSearches;
    }


    static Search getLastSearch(){
        try{
            return lastSearches.get(0);}
        catch(IndexOutOfBoundsException e){
            return new Search("");}}


    public static List<Accommodation> search(Search search){
        List<Accommodation> result = new ArrayList<>();


        if (search.getMainSearch().equals("")) {
            return Accommodation.getAccommodations();
        }

        for (Accommodation accommodation: Accommodation.getAccommodations()){

            String resultString = accommodation.getAddress() +
                    accommodation.getAccommodationHouseType() +
                    accommodation.getRegions() +
                    accommodation.getAccommodationHost();

            if(resultString.contains(search.getMainSearch())) {
                result.add(accommodation);
            }
        }

        return result;
    }
}
