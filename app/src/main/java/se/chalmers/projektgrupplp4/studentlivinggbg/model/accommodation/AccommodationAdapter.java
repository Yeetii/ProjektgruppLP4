package se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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
}
