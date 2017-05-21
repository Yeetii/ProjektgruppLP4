package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SettingsController;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SettingsView;

public class SettingsActivity extends ActivityWithNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SettingsView settingsView = new SettingsView(this);
        new SettingsController(this, settingsView);
        initializeNavigationListener();

    }

}
