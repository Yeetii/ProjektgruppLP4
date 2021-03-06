package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SettingsModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherList;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Db4oDatabase;

/**
 * @author Jonathan Gildevall
 * revised by Peter Gärdenäs
 * Used by: SettingsActivity
 * Uses: SettingsModel, SearchWatcher, SearchWathcerModel, Db4oDatabse
 * Responsibilty: Controller for settings.
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
                db.deleteAll(SearchWatcher.class);
                SearchWatcherList.getSearchWatchers().clear();
                db.close();
            }
        });

    }
}
