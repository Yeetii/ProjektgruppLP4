package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import java.util.ArrayList;
import se.chalmers.projektgrupplp4.studentlivinggbg.MultiSpinner;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Region;

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
