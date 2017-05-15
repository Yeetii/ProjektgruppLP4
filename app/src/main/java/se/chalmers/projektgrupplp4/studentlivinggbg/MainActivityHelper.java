package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.List;
import se.chalmers.projektgrupplp4.studentlivinggbg.backgroundtasks.AlarmTimeManger;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Settings;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.imagemodel.ImageModel;

public class MainActivityHelper {
    private Thread dbThread;

    public MainActivityHelper(Context context) {
        new SearchHandler();
        new Settings();
        CreateDrawableHelper createDrawableHelper = new CreateDrawableHelper(context);
        ImageModel.<Drawable>getInstance().setHelper(createDrawableHelper);
        loadDatabase(context);
    }

    private void loadDatabase(final Context context) {
        //Throws error if not done in a thread.
        dbThread = (new Thread() {

            @Override
            public void run() {
                Db4oDatabase db = Db4oDatabase.getInstance();
                List<Accommodation> temp = db.findAll(Accommodation.class);
                //Should happen the first time the user starts the app
                if (temp.size() == 0) {
                    AlarmTimeManger.getInstance().setUpInstantAlarm(context);
                }

                Accommodation.getAccommodations().clear();
                Accommodation.getAccommodations().addAll(removeNullFrom(temp));

                SearchWatcherModel.getSearchWatcherItems().clear();
                SearchWatcherModel.getSearchWatcherItems().addAll(db.<SearchWatcherItem>findAll(SearchWatcherItem.class));

                db.close();

                Long currentTime = System.currentTimeMillis();
                ImageModel.getInstance().getAndSaveImages(true, Accommodation.getAccommodations());
                System.out.println("Find timestamp: " + (System.currentTimeMillis() - currentTime));
            }
        });

        dbThread.start();
    }

    private List<Accommodation>  removeNullFrom(List<Accommodation> temp) {
        List<Accommodation> tempResult = new ArrayList<>();

        for(Accommodation looper : temp){
            try{
            if(looper != null && !(looper.getAddress().equals(""))){
                tempResult.add(looper);
            }}
            catch(NullPointerException e){}
        }
        return tempResult;
    }

    public void saveDatabase() {
        Db4oDatabase db = Db4oDatabase.getInstance();
        db.deleteAll(Accommodation.class);
        for (int i = 0; i < Accommodation.getAccommodations().size(); i++) {
            db.store(Accommodation.getAccommodations().get(i));
        }
        db.close();
    }

    public Thread getDbThread() {
        return dbThread;
    }
}
