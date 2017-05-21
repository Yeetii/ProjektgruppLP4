package se.chalmers.projektgrupplp4.studentlivinggbg.service;


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
import java.util.LinkedList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.ImageModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;

/**
 * Created by PG on 13/05/2017.
 */

public class ImageHandler {
    private File directory;
    private Context context;
    private List<Thread> threads = new LinkedList<>();

    public ImageHandler(Context context) {
        this.context = context;
        ContextWrapper cw = new ContextWrapper(context);
        directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
    }

    public void getAndSaveImages (boolean loadFromDisc, List<Accommodation> accommodations) {
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


    private void loadImageFromServer (final Accommodation accommodation) {
        //Has (and should) be done in threads. First get the image from server. Then save it locally.
        Thread thread = new Thread() {
            @Override
            public void run () {
                try {
                    InputStream is = (InputStream) new URL(accommodation.getThumbnail()).getContent();
                    Drawable image = createImage(is, accommodation.getImagePath());
                    ImageModel.<Drawable>getInstance().addImage(accommodation.getImagePath(), image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        //To avoid race-conditions, save the threads and later join them.
        threads.add(thread);
    }

    public Drawable createImage(InputStream is, String path) {
        Drawable drawable = Drawable.createFromStream(is, path);
        saveToInternalStorage(path, drawable);
        return drawable;
    }

    public void loadImage(String path) {
        try {
            File imageFile = new File(directory, path);
            Drawable image = Drawable.createFromStream(new FileInputStream(imageFile), path);
            ImageModel.<Drawable>getInstance().addImage(path, image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void changeDirectory(String directoryName) {
        ContextWrapper cw = new ContextWrapper(context);
        directory = cw.getDir(directoryName, Context.MODE_PRIVATE);
    }


    private void saveToInternalStorage(String path, Drawable drawable) {
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
