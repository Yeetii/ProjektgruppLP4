package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.Model.MainModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.SearchWatcher;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

/**
 * Created by PG on 03/04/2017.
 */

public class SearchWatcherActivity extends FragmentActivity {

    ListView listView;
    private SearchWatcherAdapter adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    Intent search = new Intent(SearchWatcherActivity.this, MainSearchActivity.class);
                    search.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(search);
                    return true;
                case R.id.navigation_favorites:
                    Intent favorites = new Intent(SearchWatcherActivity.this, FavoritesActivity.class);
                    favorites.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(favorites);
                    return true;
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_watcher);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_notifications);
        Log.d("Test", "Activity created!");

        adapter = new SearchWatcherAdapter(getApplicationContext(), new ArrayList<SearchWatcher>());
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        try{displaySearch();}catch(Exception e){}



        fm = getFragmentManager();

        FragmentManager fm = getFragmentManager();
        ConstraintLayout searchWatcherBackground = (ConstraintLayout) findViewById(R.id.backgroundModal);

        //This is super bad code, remove before submit!
        ConstraintLayout searchWatcherContent = (ConstraintLayout) findViewById(R.id.constraintLayout);

        searchWatcherContent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Don't do anything!");
            }
        });


        ImageButton imageButton = (ImageButton) findViewById(R.id.closeModalButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toggle();
            }
        });

        searchWatcherBackground.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.backgroundModal) toggle();
            }
        });


    }

    private static FragmentManager fm;
    private static boolean isModalVisible = true;


    public static void toggle() {
        if (isModalVisible) {
            makeModalInvisible();
        } else {
            makeModalVisible();
        }

    }

    public static void makeModalVisible() {
        fm.beginTransaction().show(fm.findFragmentById(R.id.searchWatcherModal)).commit();
        isModalVisible = true;
    }


    public static void makeModalInvisible() {
        fm.beginTransaction().hide(fm.findFragmentById(R.id.searchWatcherModal)).commit();
        isModalVisible = false;
    }

    private void displaySearch(){
        adapter.clear();
        adapter.addAll(MainModel.getInstance().getSearchWatchers());
    }
}