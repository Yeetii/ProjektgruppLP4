package se.chalmers.projektgrupplp4.studentlivinggbg;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Region;

/**
 * Created by PG on 15/04/2017.
 */

public class SGSAdapter extends AccommodationAdapter {
    //Has to use SGS variable names
    private SGSJsonAccommodation[] Result;
    private String ObjectMainGroupDescription;
    private int ObjectMainGroupNo;
    private int TotalCount;

    @Override
    public List<Accommodation> getAccommodations() {
        List<Accommodation> accommodations = new ArrayList<>();
        for (int i = 0; i < Result.length; i++) {
            accommodations.add(convertAccommodation(Result[i]));
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
        private String AreaNo;
        private String ArriveMarketPlace; //Actually timestamp
        private String ArriveMarketPlaceDateString;
        private String BoardNo;
        private int CompanyNo;
        private int CountInterest;
        private String Description;
        private String DesiredFreeFrom; //Timepstamp
        private String DesiredFreeFromString;
        private String EndPeriodMP;
        private String EndPeriodMPDateString;
        private String EntranceNo;
        private String FirstEstateImageUrl;
        private String FirstInfoText;
        private String FirstInfoTextShort;
        private String FreeFrom;
        private String HouseFormNo;
        private String HouseForms;
        private String HouseNo;
        private String InterestOpens;
        private String InterestOpensDateString;
        private boolean IsTradingAdvertisement;
        private String Latitude;
        private String Longitude;
        private String MapUrl;
        private String MarketPlaceDescription;
        private int MarketPlaceNo;
        private String ObjectArea;
        private int ObjectAreaSort;
        private String ObjectFloor;
        private String ObjectMainGroupDescription;
        private int ObjectMainGroupNo;
        private String ObjectNo;
        private String ObjectSubDescription;
        private String ObjectSubGroupDescription;
        private int ObjectSubGroupNo;
        private ArrayList<String> ObjectTags;
        private String ObjectTypeDescription;
        private String PlaceName;
        private String Properties;
        private int PropertyNo;
        private String PublishingDate;
        private String PublishingDateString;
        private int QueueCutShow;
        private String QueueDate;
        private String QueueDateString;
        private int QueuePoints;
        private int QueuePositionCut;
        private boolean RankUpInSearchResult;
        private String RentPerMonth;
        private int RentPerMonthSort;
        private String RowId;
        private String SeekAreaDescription;
        private int SeekAreaNo;
        private String SeekAreaUrl;
        private String SellerNote;
        private String StatusDescriptionClient;
        private String Street;
        private String StreetChar;
        private String StreetName;
        private int StreetNo;
        private String SyndicateHouseFormDescriptionGrouped;
        private String SyndicateMarketPlaceImageAlt;
        private int SyndicateNo;
        private String SyndicatePropertyDescriptionGrouped;
        private int UseFilter;

        public String getPublishingDate() {return parseDate(PublishingDate);}

        public String getEndPeriod() {return parseDate(EndPeriodMPDateString);}

        private String parseDate(String string){
            return string.substring(8,10)+string.substring(4,8)+string.substring(0,4);
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