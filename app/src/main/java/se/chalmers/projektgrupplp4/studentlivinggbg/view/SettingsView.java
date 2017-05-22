package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import android.support.design.widget.BottomNavigationView;
import android.widget.CheckBox;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by Jonathan on 17/05/2017.
 */

public class SettingsView {

    private final Activity activity;
    private CheckBox pushNotifications;

    public SettingsView (Activity activity) {
        this.activity = activity;
        activity.setContentView(R.layout.activity_settings);
        setSelectedNavigationItem();
    }

    private void setSelectedNavigationItem () {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_settings);
    }


}
