package se.chalmers.projektgrupplp4.studentlivinggbg;

import org.junit.Test;
import static org.junit.Assert.*;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.SettingsModel;

/**
 * Created by Jonathan on 27/05/2017.
 */

public class TestSettings {

    @Test
    public void testSettings () {
        SettingsModel.setSettingsModel(new SettingsModel());
        assertNotNull(SettingsModel.getInstance());
        SettingsModel.getInstance().setDefaultSort("A-Ö");
        assertTrue(SettingsModel.getInstance().getDefaultSort().equals("A-Ö"));
        SettingsModel.getInstance().setPushEnabled(true);
        assertTrue(SettingsModel.getInstance().isPushEnabled());

    }
}
