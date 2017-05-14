package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.MainController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;

public class ImageModel {
    private static final HashMap<String, Drawable> mainImages = new HashMap<>();
    private static final ImageModel INSTANCE = new ImageModel();

    private List<Thread> threads = new ArrayList<>();

    public static ImageModel getInstance() {
        return INSTANCE;
    }


    public Drawable getMainImage(String objectNumber) {
        return mainImages.get(objectNumber);
    }

    public void getAndSaveImages (boolean loadFromDisc, List<Accommodation> accommodations, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        List<String> addedFiles = new ArrayList<>();
        threads.clear();
        for (int i = 0; i < accommodations.size(); i++) {
            Accommodation accommodation = accommodations.get(i);

            //Only load each file once.
            if (addedFiles.contains(accommodation.getImagePath())) {
                continue;
            }
            addedFiles.add(accommodation.getImagePath());

            File imageFile = new File(directory, accommodation.getImagePath());
            //Check if file exists, load directly from disc then otherwise load from server.
            if(imageFile.exists() && !imageFile.isDirectory()) {
                //Only want to save new images to disc.
                if (!loadFromDisc) {
                    continue;
                }
                loadImage(accommodation.getImagePath());
            } else {
                loadImageFromServer(accommodation);
            }
        }

        //Join all threads to avoid race conditions.
        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All images should be done!");
    }

    private void loadImage (String path) {
        ContextWrapper cw = new ContextWrapper(MainController.applicationContext);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        try {
            File imageFile = new File(directory, path);
            mainImages.put(path,Drawable.createFromStream(new FileInputStream(imageFile), path));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    private void loadImageFromServer (final Accommodation accommodation) {
        //Has (and should) be done in threads. First get the image from server. Then save it locally.
        Thread thread = new Thread() {
            @Override
            public void run () {
                try {
                    InputStream is = (InputStream) new URL(accommodation.getThumbnail()).getContent();
                    mainImages.put(accommodation.getImagePath(),Drawable.createFromStream(is, accommodation.getImagePath()));
                    saveToInternalStorage(accommodation.getImagePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        //To avoid race-conditions, save the threads and later join them.
        threads.add(thread);
    }

    //To slow to download the image each time, better to save to disc.
    //http://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android
    private void saveToInternalStorage(String path){
        ContextWrapper cw = new ContextWrapper(MainController.applicationContext);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        Drawable drawable = mainImages.get(path);
        Bitmap bitmapImage = ((BitmapDrawable) drawable).getBitmap();

        File myPath=new File(directory, path);

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
}
