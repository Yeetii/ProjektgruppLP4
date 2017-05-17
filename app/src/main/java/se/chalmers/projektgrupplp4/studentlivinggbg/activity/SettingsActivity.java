package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;

import se.chalmers.projektgrupplp4.studentlivinggbg.BottomNavigationListener;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SettingsController;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SearchActivityView;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SettingsView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SettingsView settingsView = new SettingsView(this);
        new SettingsController(this, settingsView);

    }

}
