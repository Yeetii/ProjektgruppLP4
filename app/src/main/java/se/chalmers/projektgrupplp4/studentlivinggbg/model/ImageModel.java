package se.chalmers.projektgrupplp4.studentlivinggbg.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Peter
 */

public class ImageModel<ImageType> {
    private Map<String, ImageType> mainImages;
    private static ImageModel INSTANCE;

    public static <T> ImageModel<T> getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageModel<>();
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
