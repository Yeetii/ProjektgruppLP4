package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.content.Context;

import se.chalmers.projektgrupplp4.studentlivinggbg.Db4oDatabase;

/**
 * Created by PG on 11/04/2017.
 */

public class MainController {
    public static Context applicationContext;

    public MainController(Context context) {
        Db4oDatabase.getInstance().setContext(context);
        applicationContext = context;
    }




}
