package se.chalmers.projektgrupplp4.studentlivinggbg.backgroundTasks;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import se.chalmers.projektgrupplp4.studentlivinggbg.AccommodationAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.ChalmersAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.NetworkHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.RequestAccommodations;
import se.chalmers.projektgrupplp4.studentlivinggbg.SGSAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.ImageModel;

/**
 * Created by PG on 07/05/2017.
 */

class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Update accommodations received");
        DatabaseUpdater.getInstance().update(context);
    }
}
