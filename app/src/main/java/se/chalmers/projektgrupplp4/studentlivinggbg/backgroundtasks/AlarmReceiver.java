package se.chalmers.projektgrupplp4.studentlivinggbg.backgroundtasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author Peter Gärdenäs
 * Used by: AlarmTimeManger
 * Uses: DatabaseUpdater
 * Responsbility: Receives alarms and calls the database to update.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Update accommodations received");
        DatabaseUpdater.getInstance().update(context);
    }
}
