package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.AdvancedSearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AdvancedSearchActivityView;

public class AdvancedSearchActivity extends ActivityWithNavigation{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AdvancedSearchActivityView(this);
        new AdvancedSearchActivityController(this);
        initializeNavigationListener();
    }
}


