package se.chalmers.projektgrupplp4.studentlivinggbg;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.MainModel;

/**
 * Created by PG on 15/04/2017.
 */

public class SGSAdapter implements AccommodationAdapter {
    private SGSJsonAccommodation[] Result;
    private String ObjectMainGroupDescription;
    private int ObjectMainGroupNo;
    private int TotalCount;

    public void updateAccommodations () {
        List<Accommodation> accommodations = Accommodation.accommodations;

        for (int i = 0; i < Result.length; i++) {
            boolean alreadyExists = false;
            for (int y = 0; y < accommodations.size(); y++) {
                if (accommodations.get(y).getObjectNumber().equals(Result[i].getObjectNumber())) {
                    alreadyExists = true;
                    updateAccommodation(Result[i], accommodations.get(y));
                    break;
                }
            }

            if (!alreadyExists) {
                addAccommodation(Result[i]);
            }
        }
    }

    private void addAccommodation(SGSJsonAccommodation SGSAccommodation) {
        //Get
        String objectNumber = SGSAccommodation.getObjectNumber();
        String street = SGSAccommodation.getStreet();
        AccommodationHouseType type = SGSAccommodation.getHouseType();
        int price = SGSAccommodation.getRentPerMonthSort();
        double area = SGSAccommodation.getObjectAreaSort();
        int searchers = SGSAccommodation.getCountInterest();
        String thumbNail = SGSAccommodation.getImagePath();
        String description = SGSAccommodation.getDescription();
        AccommodationHost host = AccommodationHost.SGS;

        //Create
        Accommodation accommodation = new Accommodation(objectNumber, street, type, price, area,
                searchers, thumbNail, description, host);
        //Add
        Accommodation.accommodations.add(accommodation);
    }

    private void updateAccommodation (SGSJsonAccommodation SGSAccommodation, Accommodation accommodation) {
        //Only searchers should change from each load.
        accommodation.setSearchers(SGSAccommodation.getCountInterest());
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

        private String getObjectNumber () {
            return ObjectNo;
        }

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

            }
            return null;
        }

        public String getImagePath() {
            return "https://marknad.sgsstudentbostader.se" + FirstEstateImageUrl;
        }
    }
}