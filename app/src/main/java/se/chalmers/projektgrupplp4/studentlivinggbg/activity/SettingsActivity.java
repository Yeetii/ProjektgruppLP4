package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SettingsController;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SettingsView;

/**
 * @author Jonathan Gildevall
 * Used by: ActivityWithNavigation, activity_settings.xml.
 * Uses: SettingsController, SettingsView
 * Responsibility: Creating the settings activity.
 */

public class SettingsActivity extends ActivityWithNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SettingsView(this);
        new SettingsController(this);
        initializeNavigationListener();

    }

}
