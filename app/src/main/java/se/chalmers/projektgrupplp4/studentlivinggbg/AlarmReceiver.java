package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;

/**
 * Created by PG on 07/05/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    private long updateInterval = 3 * 1000;
    private long extraDelayTime = 3 * 1000;

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Update accommodations received");
        updateDatabase(context);
    }

    private void updateDatabase(Context context) {
        long lastUpdateTime = getLastUpdateTime();
        if (lastUpdateTime > System.currentTimeMillis() ||
                lastUpdateTime + updateInterval < System.currentTimeMillis()) {
            Db4oDatabase db = initDataBase(context);
            List<Accommodation> previousAccommodations = db.findAll();
            List<Accommodation> newAccommodations;




        } else {
            //Set next alarm so it calls 12h + 3s after last update of the database.
            createNextAlarm(lastUpdateTime + updateInterval - System.currentTimeMillis() + extraDelayTime);
        }
    }

    private void createNextAlarm(long lastUpdateTime) {
    }

    private long getLastUpdateTime() {
        return 0;
    }

    private Db4oDatabase initDataBase(Context context) {
        Db4oDatabase db = Db4oDatabase.getInstance();
        db.setContext(context);
        return db;
    }
}
