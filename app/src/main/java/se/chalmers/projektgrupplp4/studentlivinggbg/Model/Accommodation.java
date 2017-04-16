package se.chalmers.projektgrupplp4.studentlivinggbg.Model;


import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.Controller.MainController;

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

    private  Drawable tempImage = null;
    private void setTempImage (Drawable tempImage) {
        this.tempImage = tempImage;
    }

    public Drawable getImage () {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println();
                    InputStream is = (InputStream) new URL(thumbnail).getContent();
                    setTempImage(Drawable.createFromStream(is, thumbnail));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Long timestamp = System.currentTimeMillis();
        thread.start();
        try {
            thread.join();
            System.out.println(System.currentTimeMillis() - timestamp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Drawable drawable = tempImage;
        tempImage = null;
        return drawable;
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
