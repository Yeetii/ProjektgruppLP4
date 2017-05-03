package se.chalmers.projektgrupplp4.studentlivinggbg.model;


import android.graphics.drawable.Drawable;

import java.util.List;

public class Accommodation {
    //TODO Change to package private when no longer neccessary for tesing

    private String objectNumber;
    private String address;
    private AccommodationHouseType accommodationHouseType;
    private int price;
    private double area;
    private int searchers;
    private String thumbnail;
    private List<Integer> images;
    private String description;
    private AccommodationHost accommodationHost;
    private boolean isFavorite = false;

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
    }

    public String getObjectNumber() {return objectNumber;}

    public String getAddress() {
        return address;
    }


    public String getAccommodationHouseType() {
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
            //TODO: Get file name from Chalmers.
            return null;
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

    public boolean getFavorite(){return isFavorite;}

    public void addAsFavorite(){isFavorite = true;}
    public void removeAsFavorite(){isFavorite = false;}
    public void changeFavoriteStatus(){isFavorite = !isFavorite;}
}