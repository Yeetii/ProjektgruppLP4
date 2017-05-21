package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import java.util.HashMap;


public class ImageModel<ImageType> {
    private HashMap<String, ImageType> mainImages;
    private static ImageModel INSTANCE;

    public static <T> ImageModel<T> getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageModel<T>();
        }
        return INSTANCE;
    }

    public void addImage (String imagePath, ImageType image) {
        mainImages.put(imagePath, image);
    }

    private ImageModel () {
        this.mainImages = new HashMap<>();
    }

    public ImageType getMainImage(String imagePath) {
        return mainImages.get(imagePath);
    }
}
