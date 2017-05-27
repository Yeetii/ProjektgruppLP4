package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.service.ImageHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SettingsModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SearchActivityView;

/**
 * @author Peter Gärdenäs
 * Used by: ActivityWithNavigation, SearchWatcherActivity, activity_main_search.xml, content_search.xml
 * Uses: ActivityWithNavigation, ImageHandler, Db4oDatabase, SearchHandler, SettingsModel, SearchWatcherItem
 * SearchWatcherModel, AccommodationRecyclerViewAdapter, SearchActivityController, Accommodation, SearchActivityView,
 * ObjectActivity, AdvancedSearchActivity
 * Responsibility: Creating the main activity and initializing the application.
 */

public class MainSearchActivity extends ActivityWithNavigation {
    private static boolean firstTime = true;
    private static ActivityObserver observer;
    private AccommodationRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (firstTime) {
            firstTime = false;
            initSearchActivity();
        }
        adapter = new AccommodationRecyclerViewAdapter(Accommodation.getAccommodations(), ObjectActivity.class);
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
    protected void onStop() {
        Db4oDatabase.getInstance().saveAllAccommodations();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.refresh();
    }

    private void initSearchActivity() {
        new SearchHandler();
        new SettingsModel();
        ImageHandler imageHandler = new ImageHandler(getApplicationContext());
        loadDatabase(getApplicationContext(), imageHandler);
    }

    private void loadDatabase(final Context context,final ImageHandler imageHandler) {
        final Db4oDatabase db = Db4oDatabase.getInstance();
        db.setContext(context);

        List<SettingsModel> items = db.findAll(SettingsModel.class);
        if (items.size() > 0 && items.get(0).getDefaultSort() != null) {
            SettingsModel.setSettingsModel(items.get(0));
        } else {
            SettingsModel model = new SettingsModel();
            SettingsModel.setSettingsModel(model);
            model.setDefaultSort("Pris ↓");
            db.store(model);
        }
        db.close();

        //Throws error if not done in a thread.
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Accommodation> accommodations = db.findAll(Accommodation.class);
                //Should happen the first time the user starts the app
                if (accommodations.size() == 0 && observer != null) {
                    observer.update(MainSearchActivity.this);
                }

                SearchWatcherModel.getSearchWatcherItems().clear();
                SearchWatcherModel.getSearchWatcherItems().addAll(db.<SearchWatcherItem>findAll(SearchWatcherItem.class));

                Long currentTime = System.currentTimeMillis();
                imageHandler.getAndSaveImages(true, accommodations);
                System.out.println("Find timestamp: " + (System.currentTimeMillis() - currentTime));
                SearchActivityController.updateAccommodations(accommodations);
            }
        }).start();
    }

    public static void setObserver (ActivityObserver observer) {
        MainSearchActivity.observer = observer;
    }

}
