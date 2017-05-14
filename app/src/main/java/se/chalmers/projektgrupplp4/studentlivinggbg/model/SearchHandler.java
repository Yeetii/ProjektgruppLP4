package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import java.lang.reflect.Array;
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

    public static Search createSearch(String mainSearch, String address,
                                      ArrayList<AccommodationHouseType> possibleAccomodationHouseTypes,
                                      ArrayList<AccommodationHost> possibleAccomodationHosts,
                                      ArrayList<Region> possibleRegions,
                                      int minPrice, int maxPrice, double minArea, double maxArea, int maxSearchers,
                                      String upploadDate, String lastApplyDate, boolean addToList){

        Search result = new Search(mainSearch, address,
                possibleAccomodationHouseTypes,
                possibleAccomodationHosts,
                possibleRegions,
                minPrice, maxPrice, minArea, maxArea, maxSearchers,
                upploadDate, lastApplyDate);

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

    private List<Search> getLastSearches () {
        return lastSearches;
    }


    static Search getLastSearch(){
        try{
            return lastSearches.get(0);}
        catch(IndexOutOfBoundsException e){
            return new Search("");}}



    public static List<Accommodation> getLastSearchResults(){
        return search(getLastSearch());
    }

    public static List<Accommodation> search(Search search){
        return search(search, Accommodation.getAccommodations());
    }

    public static List<Accommodation> search(Search search, List<Accommodation> accommodations){
        List<Accommodation> result = new ArrayList<>();

        if (!isNotNull(search)) {
            return accommodations;}

        for (Accommodation accommodation: accommodations){
            //todo UpploadDate and LastApplyDate?
            if(matchesWithSearch(search, accommodation)) {
                result.add(accommodation);}
        }

        return result;
    }

    private static boolean matchesWithSearch(Search search, Accommodation accommodation) {

        if(!mainSearchMatch(search, accommodation)){return false;}
        if(!houseTypeMatch(search, accommodation)){return false;}
        if(!hostMatch(search, accommodation)){return false;}
        if(!regionMatch(search, accommodation)){return false;}
        if(!priceMatch(search, accommodation)){return false;}
        if(!areaMatch(search, accommodation)){return false;}
        if(!searchersMatch(search, accommodation)){return false;}
        return true;
    }



    private static boolean searchersMatch(Search search, Accommodation accommodation) {
        return search.getMaxSearchers() == -1 || search.getMaxSearchers() > Integer.parseInt(accommodation.getSearchers());
    }

    private static boolean areaMatch(Search search, Accommodation accommodation) {

        //Todo: Remove "0" case when seekBar-bug is gone
        double searchMaxArea = search.getMaxArea();
        if(searchMaxArea == 0){searchMaxArea = -1;}

        if(search.getMinArea() == -1 && searchMaxArea == -1){return true;}
        if(search.getMinArea() == -1){return Double.parseDouble(accommodation.getArea()) < searchMaxArea;}
        if(searchMaxArea == -1){return Double.parseDouble(accommodation.getArea()) > search.getMinArea();}
        return Double.parseDouble(accommodation.getArea()) > search.getMinArea()
                && Double.parseDouble(accommodation.getArea()) < searchMaxArea;
    }

    private static boolean priceMatch(Search search, Accommodation accommodation) {
        int searchMinPrice = search.getMinPrice();
        int searchMaxPrice = search.getMaxPrice();
        if(searchMinPrice == -1 && searchMaxPrice == -1){return true;}
        if(searchMinPrice == -1){return Integer.parseInt(accommodation.getPrice()) > searchMaxPrice;}
        if(searchMaxPrice == -1){return Integer.parseInt(accommodation.getPrice()) < searchMinPrice;}
        return Integer.parseInt(accommodation.getPrice()) > searchMinPrice
                && Integer.parseInt(accommodation.getPrice()) < searchMaxPrice;
    }

    private static boolean regionMatch(Search search, Accommodation accommodation) {
        try{return  search.getPossibleRegions().toString().equals("") ||
                Region.toStringList(search.getPossibleRegions()).contains(accommodation.getRegion());}
        catch(NullPointerException e){return true;}
    }

    private static boolean hostMatch(Search search, Accommodation accommodation) {
        try{return  search.getPossibleAccommodationHosts().toString().equals("") ||
                AccommodationHost.toStringList(search.getPossibleAccommodationHosts()).contains(accommodation.getAccommodationHost());}
        catch(NullPointerException e){return true;}
    }

    private static boolean houseTypeMatch(Search search, Accommodation accommodation) {
        try{return search.getPossibleAccomodationHouseTypes().toString().equals("") ||
                AccommodationHouseType.toStringList(search.getPossibleAccomodationHouseTypes()).contains(accommodation.getAccommodationHouseType());}
        catch(NullPointerException e){return true;}
    }

    private static boolean mainSearchMatch(Search search, Accommodation accommodation) {
        return search.getMainSearch().equals("") ||
                (accommodation.getAddress()+" "+accommodation.getAccommodationHouseType()+" "+accommodation.getRegion()+" "
                        +accommodation.getAccommodationHost()).toLowerCase().contains(search.getMainSearch().toLowerCase());
    }


    public static boolean isNotNull(Search lastSearch) {

        try {
            return lastSearch != null && (!lastSearch.getMainSearch().equals("") ||
                    !lastSearch.getAddress().equals("") ||
                    !lastSearch.getPossibleAccommodationHosts().isEmpty() ||
                    !lastSearch.getPossibleAccomodationHouseTypes().isEmpty() ||
                    !(lastSearch.getMaxArea() == -1) ||
                    !(lastSearch.getMinArea() == -1) ||
                    !(lastSearch.getMaxPrice() == -1) ||
                    !(lastSearch.getMinPrice() == -1) ||
                    !(lastSearch.getMaxArea() == -1) ||
                    !(lastSearch.getMaxSearchers() == -1) ||
                    !lastSearch.getLastApplyDate().equals("") ||
                    !lastSearch.getUpploadDate().equals(""));
        }
        catch(NullPointerException e){
            return false;
        }
    }
}
