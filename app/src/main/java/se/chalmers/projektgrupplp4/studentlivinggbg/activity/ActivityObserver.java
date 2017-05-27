package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.app.Activity;

/**
 * @author Peter Gärdenäs
 * Used by: AlarmTimeManger, MainSearchActivity
 * Responsbility: An observer with acitivity as param to create connection with classes that needs an
 * acitivty.
 */

public interface ActivityObserver {
    void update(Activity activity);
}
