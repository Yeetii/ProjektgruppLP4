package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;

import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.AdvancedSearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.MainController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AccommodationHost;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.AdvancedSearchActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Region;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AdvancedSearchActivityView;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

public class AdvancedSearchActivity extends AppCompatActivity{
    private static SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AdvancedSearchActivityView view = new AdvancedSearchActivityView(this);
        AdvancedSearchActivityModel model = new AdvancedSearchActivityModel();
        view.initLayoutManager(model);
        new AdvancedSearchActivityController(this);
        setContentView(R.layout.activity_advanced_search);
    }
}


