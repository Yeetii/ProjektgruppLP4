package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.widget.Switch;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.Controller.MainController;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.AccommodationHouseType;

/**
 * Created by PG on 20/04/2017.
 */

public class ChalmersAdapter implements AccommodationAdapter {
    public htmlObject html;


    @Override
    public void updateAccommodations() {
        //System.out.println(html.getObjectSummaryApartments());
        html.printAll();
    }

    public static byte[] getFormattedBytes(StringBuffer buffer) throws UnsupportedEncodingException {
        Long timestamp = System.currentTimeMillis();
        //Remove the jquery start and ");" at the end.
        String string = buffer.substring(buffer.indexOf("{"), buffer.length() - 2);
        string = string.replaceAll("objektsummering-TOTAL@lagenheter", "objectSummaryTotal");
        string = string.replaceAll("objektsummering-SNABB@lagenheter", "objectSummaryFast");
        string = string.replaceAll("objektsummering@lagenheter", "objectSummaryApartments");
        string = string.replaceAll("objektlistabilder@lagenheter", "objectSummaryImages");
        string = string.replaceAll("objektfilter@lagenheter", "objectFilter");
        string = string.replaceAll("objektsortering", "objectSorter");
        string = string.replaceAll("objektsummering", "objectSummary");

        System.out.println("Fromat time: " + (System.currentTimeMillis() - timestamp));
        return string.getBytes("UTF-8");
    }

    private class htmlObject {
        private String objectSummaryTotal;
        private String alert;
        private String objectSummaryFast;
        private String objectSummaryApartments;
        private String objectSummaryImages;
        private String objectFilter;
        private String objectSummary;
        private String objectSorter;

        public void printAll() {
            System.out.println("5: " + objectSummaryImages);
        }

        public List<Accommodation> getAccommodations() {
            List<Accommodation> accommodations = new ArrayList<>();
            String infoString = objectSummaryImages;
            while (infoString.contains("<div class=\"Box ObjektListItem \">")) {
                accommodations.add(parseAccommodation(infoString));
                infoString = infoString.substring(infoString.indexOf("<div class=\"Box ObjektListItem \">") + 1);
                infoString = infoString.substring(infoString.indexOf("<div class=\"Box ObjektListItem \">"));
            }

            return accommodations;
        }

        private Accommodation parseAccommodation(String infoString) {
            String objectNumber = getAttribute(infoString, "object number");
            String street = getAttribute(infoString, "street");
            AccommodationHouseType houseType = parseHouseType(getAttribute(infoString, "house type"));
            int price = parsePrice(getAttribute(infoString, "price"));
            double area = parseArea(getAttribute(infoString, "area"));
            String description = getAttribute(infoString, "description");
            String thumbnail = getAttribute(infoString, "thumbnail");

            System.out.println(objectNumber);
            System.out.println(street);
            System.out.println(houseType);
            System.out.println(price);
            System.out.println(area);
            System.out.println(description);
            System.out.println(thumbnail);

            return new Accommodation(objectNumber, street, houseType, price, area, 0, description, thumbnail, AccommodationHost.CHALMERS);
          //  return new Accommodation()
        }

        private double parseArea (String areaString) {
            return Double.parseDouble(areaString);
        }

        private int parsePrice(String priceString) {
            priceString = priceString.replace(" ", "");
            return Integer.parseInt(priceString);
        }

        private AccommodationHouseType parseHouseType (String houseTypeString) {

            switch (houseTypeString) {
                case "1 rum och kök":
                    return AccommodationHouseType.ONE_ROOM;
                case "1 rum och trinett":
                    return AccommodationHouseType.KITCHENETTE;
            }
            System.out.println("Should add to switch!: " + houseTypeString);
            return null;
        }

        public String getAttribute(String infoString, String type) {
            String firstStartTag = "";
            String secondStartTag = ">";
            String endTag = "<";

            switch (type) {
                case "object number":
                    firstStartTag = "refid=";
                    secondStartTag = "";
                    endTag = "\">";
                    break;
                case "street":
                    firstStartTag = "class=\"ObjektAdress\"";
                    secondStartTag = "d\">";
                    break;
                case "house type":
                    firstStartTag = "class=\"ObjektTyp\">";
                    secondStartTag = "d\">";
                    break;
                case "description":
                    firstStartTag = "class=\"ObjektFritext\">";
                    break;
                case "rent":
                    firstStartTag = "dd class=\"ObjektHyra\"";
                    endTag = " kr/mån";
                    break;
                case "area":
                    firstStartTag = "dd class=\"ObjektYta\"";
                    endTag = "m&sup2";
                    break;
                case "thumbnail":
                    firstStartTag = "data-src=";
                    secondStartTag = "https";
                    endTag = " ";
                    break;
            }

            infoString = infoString.substring(infoString.indexOf(firstStartTag));
            return infoString.substring(infoString.indexOf(secondStartTag), infoString.indexOf(endTag));
        };

        public String getObjectSummaryApartments () {
            return objectSummaryApartments;
        }
    }
}
