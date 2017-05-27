package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.AdvancedSearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AdvancedSearchActivityView;

/**
 * @author Erik Magnusson
 * Used by: MainSearchActivity, activity_advanced_search.xml, advanced_search_fragment.xml
 * Uses: AdvancedSearchActivityController, AdvancedSearchActivityView, ActivityWithNavigation
 * Responsibility: Creating the advanced search activity.
 */

public class AdvancedSearchActivity extends ActivityWithNavigation{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AdvancedSearchActivityView(this);
        new AdvancedSearchActivityController(this);
        initializeNavigationListener();
    }
}


