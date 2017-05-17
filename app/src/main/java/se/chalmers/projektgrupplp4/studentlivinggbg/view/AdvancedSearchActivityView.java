package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import android.content.Intent;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.AdvancedSearchActivity;

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

    public static void open (Activity activity) {
            Intent intent = new Intent(activity, AdvancedSearchActivity.class);
            activity.startActivity(intent);
    }
}
