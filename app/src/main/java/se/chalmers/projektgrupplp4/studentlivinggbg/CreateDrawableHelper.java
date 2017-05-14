package se.chalmers.projektgrupplp4.studentlivinggbg;


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

import se.chalmers.projektgrupplp4.studentlivinggbg.model.imagemodel.ImageModelHelper;

/**
 * Created by PG on 13/05/2017.
 */

public class CreateDrawableHelper implements ImageModelHelper<Drawable> {
    private File directory;

    public CreateDrawableHelper (Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
    }

    public Drawable createImage(InputStream is, String path) {
        Drawable drawable = Drawable.createFromStream(is, path);
        saveToInternalStorage(path, drawable);
        return drawable;
    }

    @Override
    public Drawable loadImage(String path) throws FileNotFoundException {
        File imageFile = new File(directory, path);
        return Drawable.createFromStream(new FileInputStream(imageFile), path);
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

    @Override
    public File getDirectory() {
        return directory;
    }
}
