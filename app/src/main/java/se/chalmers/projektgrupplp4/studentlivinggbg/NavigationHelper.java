package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import se.chalmers.projektgrupplp4.studentlivinggbg.activity.AdvancedSearchActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.MainSearchActivity;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

/**
 * Created by PG on 17/05/2017.
 */

public class NavigationHelper {
    private static NavigationHelper instance;
    private Context context;

    private NavigationHelper(Context context) {
        this.context = context;
    }

    public static NavigationHelper getInstance(Context context) {
        if (instance == null) {
            instance = new NavigationHelper(context);
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
