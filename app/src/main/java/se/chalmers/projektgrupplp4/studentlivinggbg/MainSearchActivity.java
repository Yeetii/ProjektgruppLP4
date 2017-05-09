package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.MainController;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.MainModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SearchActivityView;

public class MainSearchActivity extends AppCompatActivity {
    private AccommodationRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainModel.getInstance().loadDatabase(getApplicationContext());
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

            MainModel.dbThread.join();
            searchActivityModel.refreshAdapter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        MainModel.getInstance().save();
        Db4oDatabase.getInstance().close();
        super.onPause();
    }



}
