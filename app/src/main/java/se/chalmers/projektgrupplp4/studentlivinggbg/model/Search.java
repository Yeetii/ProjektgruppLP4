package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.EnumHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;


public class Search {

    private String mainSearch = "";
    private List<AccommodationHouseType> possibleAccommodationHouseTypes;
    private List<AccommodationHost> possibleAccommodationHosts;
    private List<Region> possibleRegions;
    private int minPrice = -1;
    private int maxPrice = -1;
    private int minArea = -1;
    private int maxArea = -1;
    private int daysUpploaded = -1;
    private int daysLeft = -1;

    public Search(String mainSearch){
        this.mainSearch=mainSearch;
    }


    public Search(String mainSearch,
                  List<AccommodationHouseType> possibleAccomodationHouseTypes,
                  List<AccommodationHost> possibleAccomodationHosts,
                  List<Region> possibleRegions,
                  int minPrice, int maxPrice, int minArea, int maxArea,
                  int daysUpploaded, int daysLeft) {

        this.mainSearch = mainSearch;
        this.possibleAccommodationHouseTypes = possibleAccomodationHouseTypes;
        this.possibleAccommodationHosts = possibleAccomodationHosts;
        this.possibleRegions = possibleRegions;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minArea = minArea;
        this.maxArea = maxArea;
        this.daysUpploaded = daysUpploaded;
        this.daysLeft = daysLeft;
    }


    public String getMainSearch() {
        return mainSearch;
    }

    public List<AccommodationHouseType> getPossibleAccomodationHouseTypes() {
        return possibleAccommodationHouseTypes;
    }

    public List<AccommodationHost> getPossibleAccommodationHosts() {
        return possibleAccommodationHosts;
    }

    public List<Region> getPossibleRegions() {
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

    public int getDaysUpploaded(){
        return daysUpploaded;
    }

    public int getDaysLeft(){
        return daysLeft;
    }





    public List<Accommodation> search(){
        return search(Accommodation.getAccommodations());
    }

    public List<Accommodation> search(List<Accommodation> accommodations){
        List<Accommodation> result = new ArrayList<>();

        if (this.isEmpty()) {
            return accommodations;}

        for (Accommodation accommodation: accommodations){
            if(matchesWithSearch(accommodation)) {
                result.add(accommodation);}
        }
        return result;
    }

    private boolean matchesWithSearch(Accommodation accommodation) {

        if(!mainSearchMatch(accommodation)){return false;}
        if(!houseTypeMatch(accommodation)){return false;}
        if(!hostMatch(accommodation)){return false;}
        if(!regionMatch(accommodation)){return false;}
        if(!priceMatch(accommodation)){return false;}
        if(!areaMatch(accommodation)){return false;}
        if(!upploadDayMatch(accommodation)){return false;}
        return daysLeftMatch(accommodation);
    }


    private boolean mainSearchMatch(Accommodation accommodation) {
        if(getMainSearch().equals("")){return true;}

        String[] mainSearchArray = getMainSearch().toLowerCase().split("\\s+");
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

    private boolean houseTypeMatch(Accommodation accommodation) {
        try{
            return getPossibleAccomodationHouseTypes().size() == 0 || getPossibleAccomodationHouseTypes().toString().equals("") ||
                EnumHelper.toString(getPossibleAccomodationHouseTypes()).contains(accommodation.getAccommodationHouseType());}
        catch(NullPointerException e){return true;}
    }

    private boolean hostMatch(Accommodation accommodation) {
        try{
            return getPossibleAccommodationHosts().size() == 0 || getPossibleAccommodationHosts().toString().equals("") ||
                EnumHelper.toString(getPossibleAccommodationHosts()).contains(accommodation.getAccommodationHost());}
        catch(NullPointerException e){return true;}
    }

    private boolean regionMatch(Accommodation accommodation) {
        try{
            return  getPossibleRegions().size() == 0 || getPossibleRegions().toString().equals("") ||
                EnumHelper.toString(getPossibleRegions()).contains(accommodation.getRegion());}
        catch(NullPointerException e){return true;}
    }

    private boolean priceMatch(Accommodation accommodation) {
        int searchMinPrice = getMinPrice();
        int searchMaxPrice = getMaxPrice();
        if(searchMinPrice == -1 && searchMaxPrice == -1){return true;}
        if(searchMinPrice == -1){return Integer.parseInt(accommodation.getPrice()) > searchMaxPrice;}
        if(searchMaxPrice == -1){return Integer.parseInt(accommodation.getPrice()) < searchMinPrice;}
        return Integer.parseInt(accommodation.getPrice()) > searchMinPrice
                && Integer.parseInt(accommodation.getPrice()) < searchMaxPrice;
    }

    private boolean areaMatch(Accommodation accommodation) {

        double searchMaxArea = getMaxArea();
        if(searchMaxArea == 0){searchMaxArea = -1;}

        if(getMinArea() == -1 && searchMaxArea == -1){return true;}
        if(getMinArea() == -1){return Double.parseDouble(accommodation.getArea()) < searchMaxArea;}
        if(searchMaxArea == -1){return Double.parseDouble(accommodation.getArea()) > getMinArea();}
        return Double.parseDouble(accommodation.getArea()) > getMinArea()
                && Double.parseDouble(accommodation.getArea()) < searchMaxArea;
    }

    private boolean upploadDayMatch(Accommodation accommodation) {
        if(getDaysUpploaded() == 7 || getDaysUpploaded() == -1){return true;}
        return parseDays(new Date().toString(), accommodation.getUploadDate(), getDaysUpploaded());
    }

    private boolean daysLeftMatch(Accommodation accommodation) {
        if(getDaysLeft() == 7 || getDaysLeft() == -1){return true;}
        return parseDays(accommodation.getLastApplyDate(), new Date().toString(), getDaysLeft());
    }



    private static boolean parseDays(String higherDate, String lowerDate, int daysDelta) {
        if(higherDate.length() > lowerDate.length()){
            higherDate = parseDate(higherDate);
        }else{
            lowerDate = parseDate(lowerDate);
        }

        int higherDay = Integer.valueOf(higherDate.substring(0,2));
        int lowerDay = Integer.valueOf(lowerDate.substring(0,2));
        int higherMonth = Integer.valueOf(higherDate.substring(3,5));
        int lowerMonth = Integer.valueOf(lowerDate.substring(3,5));
        int higherYear = Integer.valueOf(higherDate.substring(6,10));
        int lowerYear = Integer.valueOf(lowerDate.substring(6,10));


        if(higherYear == lowerYear){
            if(higherMonth == lowerMonth){
                return (higherDay - lowerDay) <= daysDelta;
            }
            if(higherMonth == lowerMonth+1){
                return (daysInMonth(higherMonth, higherYear)+higherDay-lowerDay) <= daysDelta;
            }
            return false;
        }
        if(higherYear == lowerYear+1 && higherMonth == 1 && lowerMonth == 12){
            return (31+higherDay-lowerDay) <= daysDelta;
        }
        return false;

    }



    private static String parseDate(String input) {
        String result = parseDateDay(input);
        result = result+"-"+parseDateMonth(input);
        result = result+"-"+parseDateYear(input);
        return result;
    }

    private static String parseDateDay(String string) {
        return string.substring(8,10);
    }

    private static String parseDateMonth(String string) {
        string = string.substring(4,7);
        switch(string){
            case "Jan": string = "01";
                break;
            case "Feb": string = "02";
                break;
            case "Mar": string = "03";
                break;
            case "Apr": string = "04";
                break;
            case "May": string = "05";
                break;
            case "Jun": string = "06";
                break;
            case "Jul": string = "07";
                break;
            case "Aug": string = "08";
                break;
            case "Sep": string = "09";
                break;
            case "Oct": string = "10";
                break;
            case "Nov": string = "11";
                break;
            case "Dec": string = "12";
                break;
        }
        return string;
    }

    private static String parseDateYear(String string) {
        try{
        return string.substring(30,34);}
        catch(StringIndexOutOfBoundsException e){
            return string.substring(25, 29);
        }
    }

    private static int daysInMonth(int month, int year) {
        if(month == 2){
            if(year % 4 == 0){
                return 29;
            }else{
                return 28;
            }}
        if(month >= 8){
            if(month % 2 == 0){
                return 31;
            }else{
                return 30;
            }
        }else{
            if(month % 2 == 0){
                return 30;
            }else{
                return 31;
            }
        }
    }




    public boolean isEmpty() {

        try {
            return (this.getMainSearch().equals("") &&
                    this.getPossibleAccommodationHosts().isEmpty() &&
                    this.getPossibleAccomodationHouseTypes().isEmpty() &&
                    (this.getMaxArea() == -1) &&
                    (this.getMinArea() == -1) &&
                    (this.getMaxPrice() == -1) &&
                    (this.getMinPrice() == -1) &&
                    (this.getMaxArea() == -1) &&
                    (this.getDaysUpploaded() == -1) &&
                    (this.getDaysLeft() == -1));
        }
        catch(NullPointerException e){
            return true;
        }
    }


}
