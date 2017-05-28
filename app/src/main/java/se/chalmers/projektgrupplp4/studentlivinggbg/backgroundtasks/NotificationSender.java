package se.chalmers.projektgrupplp4.studentlivinggbg.backgroundtasks;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SettingsModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Db4oDatabase;

/**
 * @author Erik Magnusson
 * Used by: DatabaseUpdater
 * Uses: SettingsModel, Db4oDatabase
 * Responsibilty: Send a push nortification about new accommodations matching searchwatchers.
 */

public class NotificationSender {
    private static int highestId = 0;
    public static void sendNotification (Context context, int matches){

        //Check if push notifications are enabled.
        Db4oDatabase db = Db4oDatabase.getInstance();
        List<SettingsModel> models = db.findAll(SettingsModel.class);
        if (models.size() == 0 || !models.get(0).isPushEnabled()) return;

        //In case we want to update the notification, access it with mId.
        int mId = ++highestId;
        NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.house_image3)
                .setContentTitle("Studentbostäder Göteborg")
                .setContentText(matches +  " nya matchningar på dina bevakningar.");

        //Sends an event to the application when clicked
        final Intent broadcastIntent = new Intent("se.chalmers.projektgrupplp4.studentlivinggbg.OPEN_SEARCH_WATCHER");
        broadcastIntent.putExtra("RequestCode", "OPEN_SEARCH_WATCHER");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(pendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mId, mBuilder.build());
    }
}
