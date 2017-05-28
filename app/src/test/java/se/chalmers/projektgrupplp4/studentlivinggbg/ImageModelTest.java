package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.junit.Test;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.ImageModel;

import static junit.framework.Assert.assertNotNull;

/**
 * @author Peter
 * revised by Jonathan
 */

public class ImageModelTest {
    @Test
    public void TestDownloadFromServer () {

        ImageModel<Drawable> imageModel = ImageModel.getInstance();
        imageModel.addImage("", new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {

            }

            @Override
            public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

            }

            @Override
            public void setColorFilter(@Nullable ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return PixelFormat.OPAQUE;
            }
        });
        assertNotNull(imageModel.getMainImage(""));


        //"ImageModelHelper" is missing

        /*
        ImageModel model = ImageModel.<Drawable>getInstance();
        ImageModelHelper<Drawable> helper = new ImageHandler(InstrumentationRegistry.getTargetContext());
        //Run throw a new random dir to be sure the images are new.
        helper.changeDirectory("testDir" + new Random(10000).nextInt());
        model.setHelper(helper);

        List<Accommodation> accommodations = new ArrayList<>();
        Accommodation one = new Accommodation("", null, null, 0, 0, 0,"https://marknad.sgsstudentbostader.se/SGS/files/jpg/thumbs/thumbM70.jpg", "", AccommodationHost.SGS, null, "", "", false);
        Accommodation two = new Accommodation("", null, null, 0, 0, 0,"https://marknad.sgsstudentbostader.se/SGS/files/jpg/thumbs/failUrl.jpg", "", AccommodationHost.SGS, null, "", "", false);
        Accommodation three = new Accommodation("", null, null, 0, 0, 0,"https://www.chalmersstudentbostader.se/bilder/?file=20170418155519_img_9411x.jpg&type=WEBBBG&width=620&height=447", "", AccommodationHost.CHALMERS, null, "", "", false);

        accommodations.add(one);
        accommodations.add(two);
        accommodations.add(three);

        model.getAndSaveImages(true, accommodations);
        assertTrue(model.getMainImage(one.getImagePath()) instanceof Drawable);
        assertTrue(model.getMainImage(two.getImagePath()) == null);
        assertTrue(model.getMainImage(three.getImagePath()) instanceof Drawable);
        */

    }
}
