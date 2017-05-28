package se.chalmers.projektgrupplp4.studentlivinggbg;

import com.google.gson.Gson;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.ChalmersAdapter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jonathan on 25/05/2017.
 * @author Jonathan
 * revised by Peter
 */

public class TestChalmersData {

    private String dirPath = "app/src/test/java/se/chalmers/projektgrupplp4/studentlivinggbg/resources/";
    private String chalmersFileName = "ChalmersData.txt";
    private String chalmersUnmodifiedFileName = "ChalmersDataUnmodified.txt";


    @Test
    public void chalmersData() {
        formatData();
        createAccommodations();
    }

    private void formatData() {
        File inputFile = new File(dirPath + chalmersUnmodifiedFileName);
        File outputFile = new File(dirPath + chalmersFileName);

        FileOutputStream os = null;
        FileInputStream is = null;
        try {
            is = new FileInputStream(inputFile);
            os = new FileOutputStream(outputFile);

        } catch (FileNotFoundException e) {
            assertFalse(true);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));


        try {
            StringBuffer sb=new StringBuffer();
            String line;
            while ((line=reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            os.write(ChalmersAdapter.getFormattedBytes(sb));
            os.flush();
            os.close();
        } catch (IOException e) {
            assertFalse(true);
        }


    }

    private void createAccommodations() {
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
            assertNotNull(adapter.getAccommodations().get(i).getHost());
            assertTrue(adapter.getAccommodations().get(i).getHost().equals("Chalmers Studentbostäder"));
            assertNotNull(adapter.getAccommodations().get(i).getHouseType());
        }
        //kolla några parametrar
        //System.out.println(adapter.getAccommodations().get(0).getAddress());
    }
}
