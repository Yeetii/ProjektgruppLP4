package se.chalmers.projektgrupplp4.studentlivinggbg;

import com.google.gson.Gson;

import junit.framework.AssertionFailedError;

import org.junit.Assert;
import org.junit.Test;
import static  org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.ChalmersAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.SGSAdapter;

/**
 * Created by Jonathan on 22/05/2017.
 */

public class TestSGSData {
    String dirPath = "app/src/test/java/se/chalmers/projektgrupplp4/studentlivinggbg/resources/";
    String sgsFileName = "SGSData.txt";
    String chalmersFileName = "";

    @Test
    public void sgsData () {
        File dataFile = new File(dirPath + sgsFileName);
        FileInputStream is = null;
        try {
            is = new FileInputStream(dataFile);
        } catch (FileNotFoundException e) {
            assertFalse(true);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        Gson gson = new Gson();
        SGSAdapter adapter = gson.fromJson(reader,SGSAdapter.class);
        for (int i = 0;i<adapter.getAccommodations().size();i++) {
            assertNotNull(adapter.getAccommodations().get(i));
            assertNotNull(adapter.getAccommodations().get(i).getAddress());
            assertNotNull(adapter.getAccommodations().get(i).getObjectNumber());
            assertNotNull(adapter.getAccommodations().get(i).getAccommodationHost());
            assertTrue(adapter.getAccommodations().get(i).getAccommodationHost().equals("SGS Studentbostäder"));
            assertNotNull(adapter.getAccommodations().get(i).getAccommodationHouseType());
        }
        //kolla några parametrar
        //System.out.println(adapter.getAccommodations().get(0).getAddress());
    }

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