package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.BottomNavigationListener;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(BottomNavigationListener.getInstance());
        navigation.setSelectedItemId(R.id.navigation_settings);
    }

}
