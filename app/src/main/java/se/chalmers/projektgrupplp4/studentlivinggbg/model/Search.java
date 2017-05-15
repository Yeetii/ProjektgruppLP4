package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import java.util.ArrayList;

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
