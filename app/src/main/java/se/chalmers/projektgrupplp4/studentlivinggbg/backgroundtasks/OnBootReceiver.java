package se.chalmers.projektgrupplp4.studentlivinggbg.backgroundtasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author Peter
 */

public class OnBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("On boot received");
        AlarmTimeManger.getInstance().setUpInstantAlarm(context);
    }
}


