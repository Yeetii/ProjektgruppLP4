package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.media.audiofx.BassBoost;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SearchView;

import se.chalmers.projektgrupplp4.studentlivinggbg.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.BottomNavigationListener;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.RecyclerViewHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SettingsModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SearchActivityView;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SettingsView;

/**
 * Created by Jonathan on 17/05/2017.
 */

public class SettingsController {

    private static SettingsController controller;

    private Activity activity;
    private SettingsView view;
    private SettingsModel model;

    private CheckBox pushNotifications;
    private Button resetDatebase;
    private Button clearSearchWatchers;

    public SettingsController(Activity activity, SettingsView view) {
        this.activity = activity;
        this.view = view;
        this.model = new SettingsModel();
        controller = this;
        initListeners();
    }

    private void initListeners() {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(BottomNavigationListener.getInstance());
        pushNotifications = (CheckBox) activity.findViewById(R.id.pushNotifications);
        pushNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //TODO activate push notifications
                } else {
                    //TODO disable push notifications
                }
            }
        });
        resetDatebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO reset database
            }
        });
        clearSearchWatchers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO clear all searchWatchers
            }
        });

    }
}
