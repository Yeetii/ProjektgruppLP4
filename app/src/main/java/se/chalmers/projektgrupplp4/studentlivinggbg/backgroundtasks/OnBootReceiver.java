package se.chalmers.projektgrupplp4.studentlivinggbg.backgroundtasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author Peter Gärdenäs
 * Used by: (None).
 * Uses: AlarmTimeManger.
 * Responsibilty: Receives a event when the mobile is booted, starts an alarm so
 * the background tasks starts.
 */

public class OnBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("On boot received");
        AlarmTimeManger.getInstance().setUpInstantAlarm(context);
    }
}


