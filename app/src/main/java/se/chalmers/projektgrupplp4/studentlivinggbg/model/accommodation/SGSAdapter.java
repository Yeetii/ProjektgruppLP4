package se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Gärdenäs
 * Used by: DatabaseUpdater
 * Uses: Accommodation, AccommodationAdapter, Region, AccommodationHost, AccommodationHouseType
 * Responsibility: Converting SGS data to Accommodation form
 */

public class SGSAdapter extends AccommodationAdapter {
    //Has to use SGS variable names
    private SGSJsonAccommodation[] Result;

    @Override
    public List<Accommodation> getAccommodations() {
        List<Accommodation> accommodations = new ArrayList<>();
        for (SGSJsonAccommodation aResult : Result) {
            accommodations.add(convertAccommodation(aResult));
        }

        return accommodations;
    }

    private Accommodation convertAccommodation(SGSJsonAccommodation SGSAccommodation) {
        //Get
        String objectNumber = SGSAccommodation.getObjectNumber();
        String street = SGSAccommodation.getStreet();
        AccommodationHouseType type = SGSAccommodation.getHouseType();
        int price = SGSAccommodation.getRentPerMonthSort();
        double area = SGSAccommodation.getObjectAreaSort();
        int searchers = SGSAccommodation.getCountInterest();
        String thumbNail = SGSAccommodation.getImagePath();
        String description = SGSAccommodation.getDescription();
        String upploadDate = SGSAccommodation.getPublishingDate();
        String lastApplyDate = SGSAccommodation.getEndPeriod();
        AccommodationHost host = AccommodationHost.SGS;
        Region region = Region.parseString(SGSAccommodation.getObjectArea());


        return new Accommodation(objectNumber, street, type, price, area,
                searchers, thumbNail, description, host, region, upploadDate, lastApplyDate, false);
    }


    private class SGSJsonAccommodation {
        private int CountInterest;
        private String Description;
        private String EndPeriodMPDateString;
        private String FirstEstateImageUrl;
        private String ObjectArea;
        private int ObjectAreaSort;
        private String ObjectNo;
        private String ObjectTypeDescription;
        private String PublishingDate;
        private int RentPerMonthSort;
        private String Street;

        public String getPublishingDate() {return parseDate(PublishingDate);}

        public String getEndPeriod() {return parseDate(EndPeriodMPDateString);}

        private String parseDate(String string){
            return string.substring(8,10)+string.substring(4,8)+string.substring(2,4);
        }

        private String getObjectNumber () {
            return ObjectNo;
        }

        private String getObjectArea(){return ObjectArea;}

        public String getStreet() {
            return Street;
        }

        public int getRentPerMonthSort() {
            return RentPerMonthSort;
        }

        public int getObjectAreaSort() {
            return ObjectAreaSort;
        }

        public String getDescription() {
            return Description;
        }

        public int getCountInterest() {
            return CountInterest;
        }

        public AccommodationHouseType getHouseType () {
            switch (ObjectTypeDescription) {
                case "1 rum och kokskåp":
                    return AccommodationHouseType.COOKING_CABINET;
                case "1 rum och kök":
                    return AccommodationHouseType.ONE_ROOM;
                case "1 rum och kokvrå":
                    return AccommodationHouseType.KITCHENETTE;
                case "2 rum och kök":
                    return AccommodationHouseType.TWO_ROOMS;
                case "2 rum och kokvrå":
                    return AccommodationHouseType.TWO_ROOMS_KITCHENETTE;
                case "3 rum och kök":
                    return AccommodationHouseType.THREE_ROOMS;
                case "4 rum och kök":
                    return AccommodationHouseType.FOUR_ROOMS;
                case "4 rum och kokvrå":
                    //80 kvadrat men inget kök...
                    return AccommodationHouseType.FOUR_ROOMS;
                case "Enkelrum med gruppkök":
                    return AccommodationHouseType.CORRIDOR;
                case "Enkelrum med kokskåp":
                    return AccommodationHouseType.COOKING_CABINET;
            }
            System.out.println("Should add to switch!: " + ObjectTypeDescription);
            return AccommodationHouseType.UNKNOWN;
        }

        public String getImagePath() {
            return "https://marknad.sgsstudentbostader.se" + FirstEstateImageUrl;
        }
    }
}