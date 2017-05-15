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
        mainActivityHelper = MainActivityHelper.getInstance(getApplicationContext());
        new MainController(getApplicationContext());

        SearchActivityView searchActivityView = new SearchActivityView(this);
        SearchActivityModel searchActivityModel = SearchActivityModel.createInstance(this);
        searchActivityView.initLayoutManager(searchActivityModel);
        new SearchActivityController(this, searchActivityModel, searchActivityView);
    }

    @Override
    protected void onDestroy() {
        mainActivityHelper.saveDatabase();
        Db4oDatabase.getInstance().close();
        super.onDestroy();
    }



}
