package se.chalmers.projektgrupplp4.studentlivinggbg.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

/**
 * Created by PG on 17/05/2017.
 * Needed to avoid circular dependencies.
 */

public class ActivitySwitcher {
    private static ActivitySwitcher instance;
    private final Context context;

    private ActivitySwitcher(Context context) {
        this.context = context;
    }

    public static ActivitySwitcher getInstance(Context context) {
        if (instance == null) {
            instance = new ActivitySwitcher(context);
        }
        return instance;
    }

    public void navigate(Class<? extends Activity> newActivityClass) {
        Intent newActivity = new Intent(context, newActivityClass);
        newActivity.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
        newActivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(newActivity);
    }
}
