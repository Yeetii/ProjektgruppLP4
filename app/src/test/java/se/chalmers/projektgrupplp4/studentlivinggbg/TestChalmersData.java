package se.chalmers.projektgrupplp4.studentlivinggbg;

import com.google.gson.Gson;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.ChalmersAdapter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jonathan on 25/05/2017.
 */

public class TestChalmersData {

    String dirPath = "app/src/test/java/se/chalmers/projektgrupplp4/studentlivinggbg/resources/";
    String chalmersFileName = "";
    @Test
    public void chalmersData () {
        File dataFile = new File(dirPath + chalmersFileName);
        FileInputStream is = null;
        try {
            is = new FileInputStream(dataFile);
        } catch (FileNotFoundException e) {
            assertFalse(true);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        Gson gson = new Gson();
        ChalmersAdapter adapter = gson.fromJson(reader,ChalmersAdapter.class);
        for (int i = 0;i<adapter.getAccommodations().size();i++) {
            assertNotNull(adapter.getAccommodations().get(i));
            assertNotNull(adapter.getAccommodations().get(i).getAddress());
            assertNotNull(adapter.getAccommodations().get(i).getObjectNumber());
            assertNotNull(adapter.getAccommodations().get(i).getAccommodationHost());
            assertTrue(adapter.getAccommodations().get(i).getAccommodationHost().equals("Chalmers Studentbostäder"));
            assertNotNull(adapter.getAccommodations().get(i).getAccommodationHouseType());
        }
        //kolla några parametrar
        //System.out.println(adapter.getAccommodations().get(0).getAddress());
    }
}
