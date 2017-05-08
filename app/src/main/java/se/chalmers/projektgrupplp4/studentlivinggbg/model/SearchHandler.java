package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import java.util.ArrayList;
import java.util.List;

class SearchHandler {

    private List<Search> lastSearches = new ArrayList<>();

    public SearchHandler () {

    }

    public Search createSearch(String mainSearch, String address,
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

    private void addToLastSearches(Search result) {
        if(lastSearches.size() >= 10){
            lastSearches.remove(lastSearches.size()-1);
        }
        lastSearches.add(0, result);
    }

    private List<Search> getLastSearches () {
        return lastSearches;
    }


}
