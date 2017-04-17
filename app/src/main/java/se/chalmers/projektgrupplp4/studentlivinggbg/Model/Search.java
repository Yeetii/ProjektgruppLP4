package se.chalmers.projektgrupplp4.studentlivinggbg.Model;

import java.util.ArrayList;
import java.util.Date;


public class Search {
    //TODO Change to package private when no longer neccessary for tesing



    private String mainSearch = "";
    private String address = "";
    private String region = "";
    private ArrayList<AccommodationHouseType> possibleAccomodationHouseTypes;
    private ArrayList<AccommodationHost> possibleAccomodationHosts;
    //private ArrayList<Areas> possibleAreas; <-- Eventuell enumklass: norr, syd, etc
    private int minPrice = -1;
    private int maxPrice = -1;
    private double minArea = -1;
    private double maxArea = -1;
    private int maxSearchers = -1;
    private String upploadDate = ""; //TODO Better date implementation
    private String lastApplyDate = "";

    public Search(String mainSearch){
        this.mainSearch=mainSearch;
    }


    public Search(String mainSearch, String address, String region,
                  ArrayList<AccommodationHouseType> possibleAccomodationHouseTypes,
                  ArrayList<AccommodationHost> possibleAccomodationHosts,
                  int minPrice, int maxPrice, double minArea, double maxArea, int maxSearchers,
                  String upploadDate, String lastApplyDate) {

        this.mainSearch = mainSearch;
        this.address = address;
        this.region = region;
        this.possibleAccomodationHouseTypes = possibleAccomodationHouseTypes;
        this.possibleAccomodationHosts = possibleAccomodationHosts;
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

    public String getRegion() {
        return region;
    }

    public ArrayList<AccommodationHouseType> getPossibleAccomodationHouseTypes() {
        return possibleAccomodationHouseTypes;
    }

    public ArrayList<AccommodationHost> getPossibleAccomodationHosts() {
        return possibleAccomodationHosts;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public double getMinArea() {
        return minArea;
    }

    public double getMaxArea() {
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

}
