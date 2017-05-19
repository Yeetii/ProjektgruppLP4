package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by Erik on 2017-05-05.
 */

public class AdvancedSearchActivityView {
    private Activity activity;

    public AdvancedSearchActivityView(Activity activity){
        this.activity = activity;
        this.activity.setContentView(R.layout.activity_advanced_search);
        new AdvancedSearchFragmentView(activity);
    }
}
