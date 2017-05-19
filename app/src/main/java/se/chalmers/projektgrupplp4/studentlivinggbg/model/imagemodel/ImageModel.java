package se.chalmers.projektgrupplp4.studentlivinggbg.model.imagemodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;

public class ImageModel<ImageType> {
    private HashMap<String, ImageType> mainImages;
    private ImageModelHelper<ImageType> helper;
    private static ImageModel INSTANCE;

    private List<Thread> threads = new ArrayList<>();

    public static <T> ImageModel<T> getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageModel<T>();
        }
        return INSTANCE;
    }

    private ImageModel () {
        this.mainImages = new HashMap<>();
    }

    public ImageType getMainImage(String objectNumber) {
        return mainImages.get(objectNumber);
    }

    public void getAndSaveImages (boolean loadFromDisc, List<Accommodation> accommodations) {
        File directory = helper.getDirectory();
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
        try {
            mainImages.put(path, helper.loadImage(path));
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
                    ImageType image = helper.createImage(is, accommodation.getImagePath());
                    mainImages.put(accommodation.getImagePath(), image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        //To avoid race-conditions, save the threads and later join them.
        threads.add(thread);
    }

    public void setHelper(ImageModelHelper<ImageType> helper) {
        this.helper = helper;
    }
}
