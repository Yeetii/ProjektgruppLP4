package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.ActivitySwitcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Db4oDatabase;

/**
 * @author Peter Gärdenäs
 * Used by: (None)
 * Uses: SearchWatcherActiviy, SearchActivityController, Accommodation, ActivitySwitcher,Db4oDatabase
 * Responsebility: Recives broadcasts target to actvities and reacts on them.
 */

public class ActivityReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String requestCode = (String) intent.getExtras().get("RequestCode");
        if (requestCode == null) return;
        else if (requestCode.equals("OPEN_SEARCH_WATCHER")) {
            ActivitySwitcher.getInstance(context).navigate(SearchWatcherActivity.class);
        } else if (requestCode.equals("UPDATE_ACCOMMODATIONS")) {
            SearchActivityController.updateAccommodations(Db4oDatabase.getInstance().<Accommodation>findAll(Accommodation.class));
        }
    }
}
