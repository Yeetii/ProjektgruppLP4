package se.chalmers.projektgrupplp4.studentlivinggbg;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.ObjectActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;

import static org.junit.Assert.*;


public class TestObjectActivityModel {

    @Test
    public void testTetAccommodations(){
        List<Accommodation> accommodationList = new ArrayList<>();
        Accommodation accommodation = new Accommodation("", "", AccommodationHouseType.COOKING_CABINET, 0, 0, 0, "", "", AccommodationHost.CHALMERS, Region.CENTER, "", "", true);
        accommodationList.add(accommodation);
        ObjectActivityModel.getAccommodations().clear();

        assertFalse(ObjectActivityModel.getAccommodations().contains(accommodation));
        ObjectActivityModel.setAccommodations(accommodationList);
        assertTrue(ObjectActivityModel.getAccommodations().contains(accommodation));
        ObjectActivityModel.setStartPosition(1);
        assertTrue(ObjectActivityModel.getStartPosition()==1);
    }

    @Test
    public void testGetters(){
        assertTrue(ObjectActivityModel.getsGSUrl().equals("https://marknad.sgsstudentbostader.se/pgLogin.aspx?rurl=pgObjectInformation.aspx%3Fcompany%3D1%26obj%"));
        assertTrue(ObjectActivityModel.getChalmersUrl().equals("https://www.chalmersstudentbostader.se/login/?returnUrl=https%3A%2F%2Fwww.chalmersstudentbostader.se%2Fsok-ledigt%2Fdetalj%2F%3Frefid%"));
    }
}
