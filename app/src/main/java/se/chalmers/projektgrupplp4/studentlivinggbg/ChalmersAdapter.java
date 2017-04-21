package se.chalmers.projektgrupplp4.studentlivinggbg;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.Controller.MainController;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.Accommodation;

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
            while (infoString.contains("hej")) {
                accommodations.add(parseAccommodation(infoString));
            }

            return accommodations;
        }

        private Accommodation parseAccommodation(String infoString) {
            String street = getAttribute(infoString, "street");
            String houseType = getAttribute(infoString, "house type");
            return null;
          //  return new Accommodation()
        }

        public String getAttribute(String infoString, String type) {
            switch (type) {
                case "street":
                    infoString = infoString.substring(infoString.indexOf("<h3 class=\"ObjektAdress\">"));
                    infoString.substring(infoString.indexOf(">") + 1, infoString.length() - 9);
                    break;
                case "house type":
                    infoString = infoString.substring(infoString.indexOf("<h3 class=\"ObjectTyp\">"));
                    infoString.substring(infoString.indexOf(">") + 1, infoString.length() - 9);
                    break;
                case "description":

            }

            return infoString;
        };

        public String getObjectSummaryApartments () {
            return objectSummaryApartments;
        }
    }
}
