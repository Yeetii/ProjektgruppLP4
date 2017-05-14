package se.chalmers.projektgrupplp4.studentlivinggbg;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;


/**
 * Created by Jonathan on 08/05/2017.
 */

public class AccommodationTest {
    //Accommodation accommodation;
    public AccommodationTest () {

    }
    @Test
    public void getFavorites () throws Exception {
        //Favorites need to be saved
        ArrayList<Accommodation> favorites = Accommodation.getFavorites();
        /*for (int i = 0;i<favorites.size();i++) {
            assertEquals(true, favorites.get(i).getFavorite());
        }*/
        for (Accommodation favorite : favorites) {
            assertTrue(favorite.getFavorite());
        }

    }

    @Test
    public void getAccommodations () throws Exception {
        //Database needs to be started?
        List<Accommodation> accommodations = Db4oDatabase.getInstance().findAllAccommodations();
        /*List<Accommodation>*/ accommodations = Accommodation.getAccommodations();
        for (int i = 0;i<accommodations.size();i++) {
            assertNotNull(accommodations.get(i));
        }

    }

    @Test
    public void update() {

    }

  /*  public void setFavorite(boolean value) {
        this.isFavorite = value;
    }

    public String getObjectNumber() {return objectNumber;}

    public String getAddress() {
        return address;
    }


    public String getAccommodationHouseType() {
        return accommodationHouseType.toString();
    }


    public String getPrice() {
        return Integer.toString(price);
    }
*/
}
