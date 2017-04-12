package se.chalmers.projektgrupplp4.studentlivinggbg.Model;

import java.util.ArrayList;
import java.util.Date;


public class Search {
    //TODO Change to package private when no longer neccessary for tesing

    private String mainSearch = "";
    private String address = "";
    private ArrayList<AccommodationHouseType> possibleAccomodationHouseTypes;
    private ArrayList<AccommodationHost> possibleAccomodationHosts;
    //private ArrayList<Areas> possibleAreas; <-- Eventuell enumklass: norr, syd, etc
    private int minPrice = 0;
    private int maxPrice = 99999;
    private double minArea = 0;
    private double maxArea = 9999;
    private int maxSearchers = 9999;
    private Date lastApplyDate = new Date();

    public Search(String mainSearch){
        this.mainSearch=mainSearch;
    }


    public Search(String mainSearch, String address,
                  ArrayList<AccommodationHouseType> possibleAccomodationHouseTypes,
                  ArrayList<AccommodationHost> possibleAccomodationHosts,
                  int minPrice, int maxPrice, double minArea, double maxArea, int maxSearchers) {

        this.mainSearch = mainSearch;
        this.address = address;
        this.possibleAccomodationHouseTypes = possibleAccomodationHouseTypes;
        this.possibleAccomodationHosts = possibleAccomodationHosts;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minArea = minArea;
        this.maxArea = maxArea;
        this.maxSearchers = maxSearchers;
    }

}
