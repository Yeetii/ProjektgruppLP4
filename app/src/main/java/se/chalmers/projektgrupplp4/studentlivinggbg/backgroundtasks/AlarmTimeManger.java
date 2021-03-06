package se.chalmers.projektgrupplp4.studentlivinggbg.backgroundtasks;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.TimeZone;

import se.chalmers.projektgrupplp4.studentlivinggbg.service.Db4oDatabase;

/**
 * @author Peter Gärdenäs
 * Used by: DatabaseUpdater
 * Uses: Db4oDatabase, AlarmReceiver.
 * Responsibilty: Sets up the next time for the database to be fetched.
 */
public class AlarmTimeManger {
    private static final AlarmTimeManger INSTANCE = new AlarmTimeManger();
    private final Calendar calendar = Calendar.getInstance();


    private AlarmTimeManger () {
        calendar.setTimeZone(TimeZone.getTimeZone("Etc/GMT+2"));
    }

    public static AlarmTimeManger getInstance() {
        return INSTANCE;
    }

    private void createAlarm(Long timeForAlarm, Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent createdIntent = new Intent(context, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, createdIntent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeForAlarm, pendingIntent);
        System.out.println("Next alarm set for: " + timeForAlarm);
    }

    private long nextUpdateTime() {
        Long lastUpdateTime = Db4oDatabase.getInstance().getTimestamp();
        long currentTime = System.currentTimeMillis();

        long updateInterval = AlarmManager.INTERVAL_DAY / 2;
        if (lastUpdateTime == null || lastUpdateTime > currentTime ||
                (lastUpdateTime + updateInterval < currentTime)) {
            return currentTime;
        }

        //Get current hour
        calendar.setTimeInMillis(currentTime);
        int currentHour = calendar.get(Calendar.HOUR);

        //Compare with last update time.
        calendar.setTimeInMillis(lastUpdateTime);
        int amountOfHours = 0;
        int amountOfMin = 0;

        if (calendar.get(Calendar.MINUTE) % 60 != 0) {
            amountOfHours--;
            amountOfMin = 60 - calendar.get(Calendar.MINUTE);
        }

        long updateIntervalInHours = 6;
        if (calendar.get(Calendar.HOUR) % updateIntervalInHours != 0) {
            amountOfHours += updateIntervalInHours - (calendar.get(Calendar.HOUR) % updateIntervalInHours);
        } else if (calendar.get(Calendar.HOUR) == currentHour) {
            amountOfHours += updateIntervalInHours;
        }
        return lastUpdateTime + 1000 * (3600 * amountOfHours + 60 * amountOfMin);
    }

    void createNextAlarm(Context context) {
        createAlarm(nextUpdateTime(), context);
    }
}
