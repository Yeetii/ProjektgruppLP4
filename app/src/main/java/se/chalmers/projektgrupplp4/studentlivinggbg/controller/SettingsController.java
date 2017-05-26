package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SettingsModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Db4oDatabase;

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
        final Db4oDatabase db = Db4oDatabase.getInstance();
        final SettingsModel model = SettingsModel.getInstance();
        CheckBox pushNotifications = (CheckBox) activity.findViewById(R.id.pushNotifications);
        pushNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                model.setPushEnabled(!model.isPushEnabled());
                db.deleteAll(model.getClass());
                db.store(model);
                db.close();
            }
        });
        pushNotifications.setChecked(model.isPushEnabled());
        Button resetDatebase = (Button) activity.findViewById(R.id.resetDatabase);
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
                db.deleteAll(SearchWatcherItem.class);
                SearchWatcherModel.updateWatchers(new ArrayList<Accommodation>());
                db.close();
            }
        });

    }
}
