package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SettingsModel;

/**
 * Created by Jonathan on 17/05/2017.
 */

public class SettingsController {

    private final Activity activity;

    public SettingsController(Activity activity) {
        this.activity = activity;
        new SettingsModel();
        initListeners();
    }

    private void initListeners() {
        CheckBox pushNotifications = (CheckBox) activity.findViewById(R.id.pushNotifications);
        pushNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SettingsModel.resetPushNotificationsEnabled();
                    //TODO activate push notifications
                } else {
                    SettingsModel.setPushNotificationsEnabled();
                    //TODO disable push notifications
                }
            }
        });
        Button resetDatebase = (Button) activity.findViewById(R.id.resetDatabase);
        SettingsModel.isPushNotificationsEnabled();
        pushNotifications.setChecked(SettingsModel.isPushNotificationsEnabled());
        resetDatebase = (Button) activity.findViewById(R.id.resetDatabase);
        resetDatebase.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO reset database
            }
        });
        Button clearSearchWatchers = (Button) activity.findViewById(R.id.removeSearchWatchers);
        clearSearchWatchers.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO remove searchWatchers
            }
        });

    }
}
