package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.graphics.drawable.Drawable;
//import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.ImageModel;
//import se.chalmers.projektgrupplp4.studentlivinggbg.model.imagemodel.ImageModelHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.ImageHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by PG on 15/05/2017.
 */

public class ImageModelTest {
    @Test
    public void TestDownloadFromServer () {

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
