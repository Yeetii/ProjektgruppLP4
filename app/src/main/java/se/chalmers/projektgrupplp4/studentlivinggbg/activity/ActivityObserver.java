package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.app.Activity;

/**
 * @author Peter Gärdenäs
 * Used by: AlarmTimeManger, MainSearchActivity
 *
 */

public interface ActivityObserver {
    void update(Activity activity);
}
