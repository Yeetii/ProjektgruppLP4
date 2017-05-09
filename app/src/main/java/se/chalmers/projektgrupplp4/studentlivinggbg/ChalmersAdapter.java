package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.widget.Switch;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.MainModel;

/**
 * Created by PG on 20/04/2017.
 */

public class ChalmersAdapter extends AccommodationAdapter {
    private htmlObject html;

    @Override
    public List<Accommodation> getAccommodations() {
        return html.getAccommodations();
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

        public List<Accommodation> getAccommodations() {
            List<Accommodation> accommodations = new ArrayList<>();
            String infoString = objectSummaryImages;
            while (infoString.contains("<div class=\"Box ObjektListItem \">")) {
                infoString = infoString.substring(infoString.indexOf("<div class=\"Box ObjektListItem \">"));
                accommodations.add(parseAccommodation(infoString));
                infoString = infoString.substring(infoString.indexOf("<div class=\"Box ObjektListItem \">") + 3);
            }

            return accommodations;
        }

        private Accommodation parseAccommodation(String infoString) {
            String objectNumber = getAttribute(infoString, "object number");
            String street = getAttribute(infoString, "street");
            AccommodationHouseType houseType = parseHouseType(getAttribute(infoString, "house type"));
            int price = parsePrice(getAttribute(infoString, "rent"));
            double area = parseArea(getAttribute(infoString, "area"));
            String description = getAttribute(infoString, "description");
            String thumbnail = createThumbnail(getAttribute(infoString, "thumbnail"));
            return new Accommodation(objectNumber, street, houseType, price, area, 0, thumbnail, description, AccommodationHost.CHALMERS, false);
        }

        private double parseArea (String areaString) {
            return Double.parseDouble(areaString);
        }

        private String createThumbnail (String fistStep) {
            fistStep = fistStep.replaceAll("amp;", "");
            fistStep = "https://" + fistStep;
            fistStep += "&width=400&height=400";
            return fistStep;
        }

        private int parsePrice(String priceString) {
            priceString = priceString.replaceAll("\\s+", "");
            return Integer.parseInt(priceString);
        }

        private AccommodationHouseType parseHouseType (String houseTypeString) {
            switch (houseTypeString) {
                case "1 rum och kök":
                    return AccommodationHouseType.ONE_ROOM;
                case "1 rum med trinett":
                    return AccommodationHouseType.KITCHENETTE;
                case "1 rum och kokvrå":
                    return AccommodationHouseType.COOKING_CABINET;
                case "2 rum och kök":
                    return AccommodationHouseType.TWO_ROOMS;

            }
            System.out.println("Should add to switch!: " + houseTypeString);
            return null;
        }

        private String getAttribute(String infoString, String type) {
            String firstStartTag = "";
            String secondStartTag = ">";
            String endTag = "<";

            switch (type) {
                case "object number":
                    firstStartTag = "refid=";
                    secondStartTag = "refid=";
                    endTag = "\">";
                    break;
                case "street":
                    firstStartTag = "class=\"ObjektAdress\"";
                    secondStartTag = "d\">";
                    endTag = "</a>";
                    break;
                case "house type":
                    firstStartTag = "class=\"ObjektTyp\">";
                    secondStartTag = "d\">";
                    endTag = "</a>";
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
                    secondStartTag = "https://";
                    endTag = "\" ";
                    break;
            }
            infoString = infoString.substring(infoString.indexOf(firstStartTag));
            return infoString.substring(infoString.indexOf(secondStartTag) + secondStartTag.length(), infoString.indexOf(endTag));
        };
    }
}
