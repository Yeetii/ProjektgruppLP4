package se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation;


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
    private String upploadDate;
    private String lastApplyDate;


    public Accommodation(String objectNumber, String address, AccommodationHouseType accommodationHouseType,
                         int price, double area, int searchers, String thumbnail, String description, AccommodationHost accommodationHost,
                         Region region, String upploadDate, String lastApplyDate, boolean addToAccommodations) {
        this.objectNumber = objectNumber;
        this.address=address;
        this.accommodationHouseType = accommodationHouseType;
        this.price=price;
        this.area=area;
        this.searchers=searchers;
        this.thumbnail=thumbnail;
        this.description = description;
        this.accommodationHost = accommodationHost;
        this.region=region;
        this.upploadDate=upploadDate;
        this.lastApplyDate=lastApplyDate;

        initAccommodations();
        if(addToAccommodations){
            addToAccommodations();
        }
    }


    private void addToAccommodations() {
        accommodations.add(this);
    }

    public static ArrayList<Accommodation> getFavorites() {
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

    public String getFurnitured() {return "nej";}

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

    public String getUpploadDate(){return upploadDate;}

    public String getLastApplyDate(){return lastApplyDate;}

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

    public static void setNewAccommodationList(List<Accommodation> newAccommodations) {
        Accommodation.getAccommodations().clear();
        Accommodation.getAccommodations().addAll(newAccommodations);
    }

    public static void transferFavoriteStatus (List<Accommodation> previousAccommodations,
                                       List<Accommodation> newAccommodations) {
        for (int i = 0; i < previousAccommodations.size(); i++) {
            for (int y = 0; y < newAccommodations.size(); y++) {
                Accommodation previousAccommodation = previousAccommodations.get(i);
                Accommodation newAccommodation = newAccommodations.get(y);

                if (previousAccommodation.getObjectNumber().equals(newAccommodation.getObjectNumber())) {
                    newAccommodation.setFavorite(previousAccommodation.getFavorite());
                    break;
                }
            }
        }
    }
}
