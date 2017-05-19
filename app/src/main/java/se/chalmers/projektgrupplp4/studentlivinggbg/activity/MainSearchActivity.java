package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.CreateDrawableHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.backgroundtasks.AlarmTimeManger;
import se.chalmers.projektgrupplp4.studentlivinggbg.database.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SettingsModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.imagemodel.ImageModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SearchActivityView;

public class MainSearchActivity extends ActivityWithNavigation {
    private static boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (firstTime) {
            firstTime = false;
            initSearchActivity();
        }
        AccommodationRecyclerViewAdapter adapter = new AccommodationRecyclerViewAdapter(Accommodation.getAccommodations(), ObjectActivity.class);
        new SearchActivityView(this, adapter);
        new SearchActivityController(this, adapter,AdvancedSearchActivity.class);
        adapter.refresh();
        try {
            initializeNavigationListener();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        saveDatabase();
        super.onDestroy();
    }

    private void initSearchActivity() {
        new SearchHandler();
        new SettingsModel();
        CreateDrawableHelper createDrawableHelper = new CreateDrawableHelper(getApplicationContext());
        ImageModel.<Drawable>getInstance().setHelper(createDrawableHelper);
        loadDatabase(getApplicationContext());
    }

    private void loadDatabase(final Context context) {
        //Throws error if not done in a thread.
        new Thread(new Runnable() {
            @Override
            public void run() {
                Db4oDatabase db = Db4oDatabase.getInstance();
                db.setContext(context);
                List<Accommodation> temp = db.findAll(Accommodation.class);
                //Should happen the first time the user starts the app
                if (temp.size() == 0) {
                    AlarmTimeManger.getInstance().setUpInstantAlarm(context);
                }

                SearchWatcherModel.getSearchWatcherItems().clear();
                SearchWatcherModel.getSearchWatcherItems().addAll(db.<SearchWatcherItem>findAll(SearchWatcherItem.class));

                db.close();

                Long currentTime = System.currentTimeMillis();
                ImageModel.getInstance().getAndSaveImages(true, Accommodation.getAccommodations());
                System.out.println("Find timestamp: " + (System.currentTimeMillis() - currentTime));
                SearchActivityController.updateAccommodations(temp);
            }
        }).start();
    }

    private void saveDatabase() {
        Db4oDatabase db = Db4oDatabase.getInstance();
        db.deleteAll(Accommodation.class);
        for (int i = 0; i < Accommodation.getAccommodations().size(); i++) {
            db.store(Accommodation.getAccommodations().get(i));
        }
        db.close();
    }

}
