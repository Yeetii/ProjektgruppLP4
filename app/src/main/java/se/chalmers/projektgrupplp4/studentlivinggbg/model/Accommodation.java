package se.chalmers.projektgrupplp4.studentlivinggbg.model;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Accommodation {
    //TODO Change to package private when no longer neccessary for tesing

    static List<Accommodation> accommodations;

    private String objectNumber;
    private String address;
    private AccommodationHouseType accommodationHouseType;
    private Region region;
    private int price;
    private double area;
    private int searchers;
    private String thumbnail;
    private List<Integer> images;
    private String description;
    private AccommodationHost accommodationHost;
    private boolean isFavorite = false;

    /*
    public Accommodation(String objectNumber, String address, AccommodationHouseType accommodationHouseType,
                         int price, double area, int searchers, String thumbnail, String description,
                         AccommodationHost accommodationHost) {
        this.objectNumber = objectNumber;
        this.address=address;
        this.accommodationHouseType = accommodationHouseType;
        this.price=price;
        this.area=area;
        this.searchers=searchers;
        this.thumbnail=thumbnail;
        this.description = description;
        this.accommodationHost = accommodationHost;

        initAccommodations();
        addToAccommodations();
    }*/

    public Accommodation(String objectNumber, String address, AccommodationHouseType accommodationHouseType,
                         int price, double area, int searchers, String thumbnail, String description,
                         AccommodationHost accommodationHost, boolean addToAccommodations) {
        this.objectNumber = objectNumber;
        this.address=address;
        this.accommodationHouseType = accommodationHouseType;
        this.price=price;
        this.area=area;
        this.searchers=searchers;
        this.thumbnail=thumbnail;
        this.description = description;
        this.accommodationHost = accommodationHost;

        initAccommodations();
        if(addToAccommodations){
            addToAccommodations();
        }
    }


    private void addToAccommodations() {
        accommodations.add(this);
    }

    public static ArrayList<Accommodation> getFavorites() {
        System.out.println(getAccommodations());
        ArrayList<Accommodation> result = new ArrayList<>();
        for (Accommodation accommodation: getAccommodations()){
            if(accommodation.getFavorite()) {
                result.add(accommodation);
            }
        }
        return result;
    }

    private static void initAccommodations() {
        try{
            if(accommodations != null && accommodations.size() >= 0){}
            else{
                accommodations = new ArrayList<>();
            }
        }catch(Exception e){
            accommodations = new ArrayList<>();
        }
    }

    public static List<Accommodation> getAccommodations(){
        initAccommodations();
        return accommodations;
    }

    public void update(Accommodation accommodation) {
        this.searchers = Integer.parseInt(accommodation.getSearchers());
    }

    public void setFavorite(boolean value) {
        this.isFavorite = value;
    }

    public String getObjectNumber() {return objectNumber;}

    public String getAddress() {
        return address;
    }

    public String getAccommodationHouseType() {
        System.out.println("WOELS" + accommodationHouseType.toString());
        return accommodationHouseType.toString();
    }

    public String getPrice() {
        return Integer.toString(price);
    }

    public String getArea() {
        return Double.toString(area);
    }

    public String getSearchers(){
        return Integer.toString(searchers);
    }

    public void setSearchers(int amount){
         searchers = amount;
    }

    public Drawable getImage () {
        return ImageModel.getInstance().getMainImage(getImagePath());
    }

    public String getImagePath () {
        if (accommodationHost.equals(AccommodationHost.SGS)) {
            return thumbnail.substring(thumbnail.indexOf("thumbs/") + "thumbs/".length());
        } else {
            return thumbnail.substring(thumbnail.indexOf("file=") + "file=".length());
        }
    }

    public String getThumbnail(){
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public String getAccommodationHost(){
        return accommodationHost.toString();
    }

    public String getRegion(){try{return region.toString();}catch(NullPointerException e){return "";}}

    public boolean getFavorite(){return isFavorite;}

    public void addAsFavorite(){isFavorite = true;}
    public void removeAsFavorite(){isFavorite = false;}
    public void changeFavoriteStatus(){isFavorite = !isFavorite;}

    public static void setNewAccommodationList(List<Accommodation> newAccommodations, Context context) {
        ImageModel.getInstance().getAndSaveImages(true, newAccommodations, context);
        Accommodation.getAccommodations().clear();
        Accommodation.getAccommodations().addAll(newAccommodations);
    }
}
