package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.MainActivityHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.MainController;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SearchActivityView;

public class MainSearchActivity extends AppCompatActivity {
    private MainActivityHelper mainActivityHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityHelper = new MainActivityHelper(getApplicationContext());

        new MainController(getApplicationContext());

        SearchActivityView searchActivityView = new SearchActivityView(this);
        SearchActivityModel searchActivityModel = SearchActivityModel.createInstance(this);
        searchActivityView.initLayoutManager(searchActivityModel);
        new SearchActivityController(this, searchActivityModel, searchActivityView);


        try {
            /*
            Wanted to use observer pattern but: "Only the original thread that created a view
            hierarchy can touch its views" And loading/creating the database seems to require
            threading.
            */

            mainActivityHelper.getDbThread().join();
            searchActivityModel.refreshAdapter();
            List<Accommodation> test = Accommodation.getAccommodations();
            List<Accommodation> test2 = test;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        mainActivityHelper.saveDatabase();
        Db4oDatabase.getInstance().close();
        super.onDestroy();
    }



}
