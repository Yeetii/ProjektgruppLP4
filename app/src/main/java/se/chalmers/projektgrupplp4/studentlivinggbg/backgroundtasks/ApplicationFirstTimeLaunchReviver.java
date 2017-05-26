package se.chalmers.projektgrupplp4.studentlivinggbg.backgroundtasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by PG on 26/05/2017.
 */

public class ApplicationFirstTimeLaunchReviver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("First launch");
        AlarmTimeManger.getInstance().setUpInstantAlarm(context);
    }
}
