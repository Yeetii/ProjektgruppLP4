package se.chalmers.projektgrupplp4.studentlivinggbg;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;

/**
 * Created by PG on 16/05/2017.
 */

public class SorterHelper {
    private static Comparator<Accommodation> priceCompare = new Comparator<Accommodation>() {
            @Override
            public int compare(Accommodation o1, Accommodation o2) {
                return Integer.compare(Integer.parseInt(o1.getPrice()), (Integer.parseInt(o2.getPrice())));
            }
    };

    private static Comparator<Accommodation> sizeCompare = new Comparator<Accommodation>() {
        @Override
        public int compare(Accommodation o1, Accommodation o2) {
            return Double.compare(Double.parseDouble(o1.getArea()), (Double.parseDouble(o2.getArea())));
        }
    };

    private static Comparator<Accommodation> nameCompare = new Comparator<Accommodation>() {
        @Override
        public int compare(Accommodation o1, Accommodation o2) {
            return o1.getAddress().compareTo(o2.getAddress());
        }
    };

    private static void sort(List<Accommodation> accommodations, boolean reversed, Comparator<Accommodation> comparator) {
        if (reversed) {
            Collections.sort(accommodations, comparator);
            Collections.reverse(accommodations);
        } else {
            Collections.sort(accommodations, comparator);
        }
    }


    public static void sortByPrice(List<Accommodation> accommodations, boolean reversed) {
        sort(accommodations, reversed, priceCompare);
    }

    public static void sortBySize(List<Accommodation> accommodations, boolean reversed) {
        sort(accommodations, reversed, sizeCompare);
    }

    public static void sortByAddress(List<Accommodation> accommodations, boolean reversed) {
        sort(accommodations, reversed, nameCompare);
    }

}
