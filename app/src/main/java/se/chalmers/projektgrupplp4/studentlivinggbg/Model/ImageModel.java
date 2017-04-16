package se.chalmers.projektgrupplp4.studentlivinggbg.Model;

import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImageModel {
    private static final HashMap<String, Drawable> mainImages = new HashMap<>();

    public static Drawable getMainImage(String objectNumber) {
        return mainImages.get(objectNumber);
    }

    public static Thread loadImages () {
        final List<Accommodation> accommodations = MainModel.getInstance().getAccommodations();
        final List<Thread> threads = new ArrayList<Thread>();
        threads.add(new Thread() {
            @Override
            public void run () {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread mainThread = new Thread() {
            @Override
            public void run () {
                for (int i = 0; i < accommodations.size(); i++) {
                    Accommodation accommodation = accommodations.get(i);
                    threads.add(putMainImage(accommodation.getObjectNumber(), accommodation.getThumbnail()));
                }
            }
        };
        mainThread.start();
        threads.get(0).start();

        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return mainThread;

    }

    private static Thread putMainImage (final String objectNumber, final String url) {
        Thread thread = new Thread() {
            @Override
            public void run () {
                try {
                    System.out.println();
                    InputStream is = (InputStream) new URL(url).getContent();
                    mainImages.put(objectNumber,Drawable.createFromStream(is, url));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        return thread;
    }
}
