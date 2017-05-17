package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;


public class Search {
    //TODO Change to package private when no longer neccessary for tesing

    private String mainSearch = "";
    private String address = "";
    private ArrayList<AccommodationHouseType> possibleAccommodationHouseTypes;
    private ArrayList<AccommodationHost> possibleAccommodationHosts;
    private ArrayList<Region> possibleRegions;
    private int minPrice = -1;
    private int maxPrice = -1;
    private int minArea = -1;
    private int maxArea = -1;
    private int maxSearchers = -1;
    private String upploadDate = ""; //TODO Better date implementation
    private String lastApplyDate = "";

    public Search(String mainSearch){
        this.mainSearch=mainSearch;
    }


    public Search(String mainSearch, String address,
                  ArrayList<AccommodationHouseType> possibleAccomodationHouseTypes,
                  ArrayList<AccommodationHost> possibleAccomodationHosts,
                  ArrayList<Region> possibleRegions,
                  int minPrice, int maxPrice, int minArea, int maxArea, int maxSearchers,
                  String upploadDate, String lastApplyDate) {

        this.mainSearch = mainSearch;
        this.address = address;
        this.possibleAccommodationHouseTypes = possibleAccomodationHouseTypes;
        this.possibleAccommodationHosts = possibleAccomodationHosts;
        this.possibleRegions = possibleRegions;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minArea = minArea;
        this.maxArea = maxArea;
        this.maxSearchers = maxSearchers;
        this.upploadDate = upploadDate;
        this.lastApplyDate = lastApplyDate;

    }


    public static List<Accommodation> search(Search search){
        return search(search, Accommodation.getAccommodations());
    }

    public static List<Accommodation> search(Search search, List<Accommodation> accommodations){
        List<Accommodation> result = new ArrayList<>();

        if (search.isEmpty()) {
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
        if(search.getMainSearch().equals("")){return true;}

        String[] mainSearchArray = search.getMainSearch().toLowerCase().split("\\s+");
        String accommodationString = (accommodation.getAddress()+" "+accommodation.getAccommodationHouseType()+" "
                +accommodation.getRegion()+" " +accommodation.getAccommodationHost()).toLowerCase();

        int i = 0;
        for(String mainSearchSubString: mainSearchArray){
            try{
                if((mainSearchSubString.equals("1") ||  mainSearchSubString.equals("2") || mainSearchSubString.equals("3") || mainSearchSubString.equals("4"))&& mainSearchArray[++i].equals("rum")){
                    mainSearchSubString = mainSearchSubString+"-"+"rum";}}
            catch(ArrayIndexOutOfBoundsException e){}
            if(!accommodationString.contains(mainSearchSubString)){
                return false;}
        }

        return true;
    }


    public String getMainSearch() {
        return mainSearch;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<AccommodationHouseType> getPossibleAccomodationHouseTypes() {
        return possibleAccommodationHouseTypes;
    }

    public ArrayList<AccommodationHost> getPossibleAccommodationHosts() {
        return possibleAccommodationHosts;
    }

    public ArrayList<Region> getPossibleRegions() {
        return possibleRegions;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public int getMinArea() {
        return minArea;
    }

    public int getMaxArea() {
        return maxArea;
    }

    public int getMaxSearchers() {
        return maxSearchers;
    }

    public String getUpploadDate() {
        return upploadDate;
    }

    public String getLastApplyDate() {
        return lastApplyDate;
    }

    public boolean isEmpty() {

        try {
            return this == null || (this.getMainSearch().equals("") &&
                    this.getAddress().equals("") &&
                    this.getPossibleAccommodationHosts().isEmpty() &&
                    this.getPossibleAccomodationHouseTypes().isEmpty() &&
                    (this.getMaxArea() == -1) &&
                    (this.getMinArea() == -1) &&
                    (this.getMaxPrice() == -1) &&
                    (this.getMinPrice() == -1) &&
                    (this.getMaxArea() == -1) &&
                    (this.getMaxSearchers() == -1) &&
                    this.getLastApplyDate().equals("") &&
                    this.getUpploadDate().equals(""));
        }
        catch(NullPointerException e){
            return true;
        }
    }
}
