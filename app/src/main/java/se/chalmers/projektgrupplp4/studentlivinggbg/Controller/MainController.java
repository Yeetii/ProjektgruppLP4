package se.chalmers.projektgrupplp4.studentlivinggbg.Controller;

import android.content.Context;

import se.chalmers.projektgrupplp4.studentlivinggbg.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.MainModel;

/**
 * Created by PG on 11/04/2017.
 */

public class MainController {
    public MainController(Context context) {
        Db4oDatabase.getInstance().setContext(context);
    }




}
