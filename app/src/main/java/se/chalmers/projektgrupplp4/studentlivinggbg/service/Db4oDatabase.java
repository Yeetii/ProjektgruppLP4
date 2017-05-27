package se.chalmers.projektgrupplp4.studentlivinggbg.service;


import android.content.Context;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;

/**
 * @author Peter Gärdenäs
 *  Revised by: Erik Magnusson
 * Used by: AdvancedSearchActivityController, AlarmTimeManger, DatabaseUpdater, FavoritesActivity,
 * ActivityReceiver, MainSearchActivity, ModalController, NortificationsSender, ObjectActivity,
 * SearchActivityContoller, SettingsController
 * Uses: Accommodation
 * Responsbility: Saves and gets all Java objects used between sessions.
 */

public class Db4oDatabase {
    private static final Db4oDatabase INSTANCE = new Db4oDatabase();

    private static ObjectContainer oc = null;
    private Context context;
    private Thread thread;


    public static Db4oDatabase getInstance() {
        return INSTANCE;
    }

    /*This feels wried to me, should we really have to open the file each time we want to access
       something? Better to just write an open/create method?
     */


    //Create, open and close the database
    public ObjectContainer db() {
        try {
            if (oc == null || oc.ext().isClosed()) {
                oc = Db4oEmbedded.openFile(dbConfig(), db4oDBFullPath(context));
            }
            return oc;
        } catch (Exception ie) {
//            Log.e(Db4oDatabase.class.getName(), ie.toString());
            ie.printStackTrace();
            return null;
        }
    }

    /**
     * Configure the behavior of the database
     */

    //Don't know what this does, we should find out! :)
    private EmbeddedConfiguration dbConfig() throws IOException {
        EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
        configuration.common().objectClass(Accommodation.class).objectField("address").indexed(true);
        configuration.common().objectClass(Accommodation.class).cascadeOnUpdate(true);
        configuration.common().objectClass(Accommodation.class).cascadeOnActivate(true);
        return configuration;
    }


    //Returns the path for the database location
    private String db4oDBFullPath(Context ctx) {
        return ctx.getDir("data", 0) + "/" + "pumpup.db4o";
    }

    public void close() {
        if (oc != null) oc.close();
    }

    public <T> void store(T exercise){
        db().store(exercise);
    }


    //Stupid but it works, didn't get it to work with string, long, custom class only contaning long/string
    //actual timestamps classes could be saved but gave strange output, stackoverflow didn't help either.
    public void storeTimestamp() {
        while (db().query(StringBuilder.class).size() != 0) {
            db().delete(db().query(StringBuilder.class).get(0));
        }
        StringBuilder builder = new StringBuilder("" + System.currentTimeMillis());
        db().store(builder);

    }

    public Long getTimestamp() {
        List<StringBuilder> timestamps = db().query(StringBuilder.class);

        if (timestamps.size() != 0) {
            return Long.parseLong(timestamps.get(0).substring(0));
            // return timestamps.get(0).getTime();
        }

        return null;
    }


    private  <T> void delete(T exercise) {
        db().delete(exercise);
    }

    public void deleteAll (Class t) {
        while (findAll(t).size() != 0) {
            delete(findAll(t).get(0));
        }
    }

    public Db4oDatabase initDataBase(Context context) {
        Db4oDatabase db = Db4oDatabase.getInstance();
        db.setContext(context);
        return db;
    }

    public void replaceAccommodationsList (List<Accommodation> newAccommodations) {
        this.deleteAll(Accommodation.class );
        this.storeTimestamp();
        for (Accommodation accommodation: newAccommodations) {
            this.store(accommodation);
        }
        this.close();
    }

    //Breaks if it t and T are not the same class.
    public <T> List<T> findAll(Class t) {
        //Sometimes throws strange exception when using the same list.
        List<T> newList = new ArrayList<>();
        newList.addAll(db().query(t));
        return newList;
    }

    //This method is used to retrive matched object from database.
    public List<Accommodation> getRecord(Accommodation s) {
        return db().queryByExample(s);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void saveAllAccommodations() {
        /*
            Not threading slows down navigation too much. Having several threads active simultaneously
            create crashes since the database might be closed unexpectedly. This is not an optimal
            solution but it works.
         */
        if (thread != null) thread.interrupt();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deleteAll(Accommodation.class);
                    for (int i = 0; i < Accommodation.getAccommodations().size(); i++) {
                        store(Accommodation.getAccommodations().get(i));
                    }
                    close();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    close();
                }
            }
        });
        thread.start();
    }
}
