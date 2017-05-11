package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.ObjectActivity;

/**
 * Created by Erik on 2017-05-11.
 */

public class ObjectActivityModel {
    static private List<Accommodation> accommodations = Accommodation.getAccommodations();

//    public ObjectActivityModel(List<Accommodation> accommodations){
//        this.accommodations = accommodations;
//    }
    public static void setAccommodations(List<Accommodation> accommodations){
        ObjectActivityModel.accommodations = accommodations;
    }

    public static List<Accommodation> getAccommodations(){
        return accommodations;
    }
}
