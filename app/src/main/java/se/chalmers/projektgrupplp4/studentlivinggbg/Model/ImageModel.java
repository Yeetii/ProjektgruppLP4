package se.chalmers.projektgrupplp4.studentlivinggbg.Model;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.Controller.MainController;

public class ImageModel {
    private static final HashMap<String, Drawable> mainImages = new HashMap<>();

    public static Drawable getMainImage(String objectNumber) {
        return mainImages.get(objectNumber);
    }

    private void loadAllInDir () {

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

    //To slow to download the image each time, better to save to disc.
    //http://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android
    private void saveToInternalStorage(Bitmap bitmapImage, String path){
        ContextWrapper cw = new ContextWrapper(MainController.applicationContext);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File myPath=new File(directory, path + ".jpg");

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(myPath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
