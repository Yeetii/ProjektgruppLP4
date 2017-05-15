package se.chalmers.projektgrupplp4.studentlivinggbg.model.imagemodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by PG on 14/05/2017.
 */

public interface ImageModelHelper<T> {
    T createImage(InputStream is, String path);
    T loadImage(String path) throws FileNotFoundException;
    File getDirectory();
    void changeDirectory(String directoryName);
}
