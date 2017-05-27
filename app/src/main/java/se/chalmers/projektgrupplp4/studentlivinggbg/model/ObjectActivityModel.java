package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;

/**
 * @author
 */

public class ObjectActivityModel {
    static private List<Accommodation> accommodations = Accommodation.getAccommodations();
    private static int startPosition;

    static private String sGSUrl = "https://marknad.sgsstudentbostader.se/pgLogin.aspx?rurl=pgObjectInformation.aspx%3Fcompany%3D1%26obj%";
    static private String chalmersUrl = "https://www.chalmersstudentbostader.se/login/?returnUrl=https%3A%2F%2Fwww.chalmersstudentbostader.se%2Fsok-ledigt%2Fdetalj%2F%3Frefid%";


    public static List<Accommodation> getAccommodations(){
        return accommodations;
    }

    public static void setAccommodations(List<Accommodation> accommodations){
        ObjectActivityModel.accommodations = accommodations;
    }

    public static String getsGSUrl() {return sGSUrl;}

    public static String getChalmersUrl() {return chalmersUrl;}

    public static int getStartPosition() {
        return startPosition;
    }

    public static void setStartPosition(int startPosition) {
        ObjectActivityModel.startPosition = startPosition;
    }
}
