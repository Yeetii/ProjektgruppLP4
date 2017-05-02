package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import se.chalmers.projektgrupplp4.studentlivinggbg.Activity.SearchWatcherActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.Controller.MainController;
import se.chalmers.projektgrupplp4.studentlivinggbg.Controller.SearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.MainModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.SearchActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.View.SearchActivityView;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

public class MainSearchActivity extends AppCompatActivity {
    private AccommodationRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MainController(getApplicationContext());

        SearchActivityView searchActivityView = new SearchActivityView(this);
        SearchActivityModel searchActivityModel = new SearchActivityModel(this);
        searchActivityView.initLayoutManager(searchActivityModel);
        new SearchActivityController(
                this,
                searchActivityModel,
                searchActivityView);

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
