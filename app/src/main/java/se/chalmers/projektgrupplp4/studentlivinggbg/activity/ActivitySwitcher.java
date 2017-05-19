package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

/**
 * Created by PG on 17/05/2017.
 */

public class ActivitySwitcher {
    private static ActivitySwitcher instance;
    private Context context;

    private ActivitySwitcher(Context context) {
        this.context = context;
    }

    public static ActivitySwitcher getInstance(Context context) {
        if (instance == null) {
            instance = new ActivitySwitcher(context);
        }
        return instance;
    }

    public void navigateToMainActivity() {
        navigate(MainSearchActivity.class);
    }

    public void navigate(Class<? extends Activity> newActivityClass) {
        Intent newActivity = new Intent(context, newActivityClass);
        newActivity.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(newActivity);
    }

    public void navigateToAdvancedSearch() {
        navigate(AdvancedSearchActivity.class);
    }


    //Needed to avoid circular dependencies.
}
