package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.support.v7.widget.Toolbar;

import android.os.Bundle;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.ObjectController;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.ObjectView;

/**
 * @author
 */

public class ObjectActivity extends ActivityWithNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_object);
        new ObjectView(this, getSupportFragmentManager());
        new ObjectController(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onStop() {
        Db4oDatabase.getInstance().saveAllAccommodations();
        super.onStop();
    }

}
