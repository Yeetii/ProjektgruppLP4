package se.chalmers.projektgrupplp4.studentlivinggbg.backgroundtasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import se.chalmers.projektgrupplp4.studentlivinggbg.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;

/**
 * Created by PG on 04/05/2017.
 */

public class OnBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("On boot received");
        AlarmTimeManger.getInstance().setUpInstantAlarm(context);
    }
}


