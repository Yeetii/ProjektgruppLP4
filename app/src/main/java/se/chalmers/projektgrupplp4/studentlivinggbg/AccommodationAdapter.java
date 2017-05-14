package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;

/**
 * Created by PG on 20/04/2017.
 */

public abstract class AccommodationAdapter {
    public void updateAccommodations() {
        List<Accommodation> adapterAccommodations = getAccommodations();
        List<Accommodation> accommodations = Accommodation.getAccommodations();

        for (int i = 0; i < adapterAccommodations.size(); i++) {
            boolean alreadyExists = false;

            for (int y = 0; y < accommodations.size(); y++) {
                if (isSameAccommodation(adapterAccommodations.get(i), accommodations.get(y))) {
                    alreadyExists = true;
                    accommodations.get(y).update(adapterAccommodations.get(i));
                    break;
                }
            }

            if (!alreadyExists) {
                accommodations.add(adapterAccommodations.get(i));
            }
        }
    };

    private boolean isSameAccommodation (Accommodation accommodationOne, Accommodation accommodationTwo) {
        return accommodationOne.getObjectNumber().equals(accommodationTwo.getObjectNumber());
    }

    public abstract List<Accommodation> getAccommodations();

    public static AccommodationAdapter getPopulatedAdapter(Class<? extends AccommodationAdapter> adapterClass,
                                                           Context context) {
        Gson gson = new Gson();
        AccommodationAdapter adapter = null;
        try {
            //Compare the classes
            String fileName = (adapterClass.isAssignableFrom(SGSAdapter.class)) ? "SGSData" : "ChalmersData";

            InputStream is = context.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            //Create a new adapter
            adapter = gson.fromJson(reader, adapterClass);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return adapter;
    }
}
