package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import se.chalmers.projektgrupplp4.studentlivinggbg.Model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.AccommodationHouseType;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.MainModel;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

public class MainSearchActivity extends AppCompatActivity {

    private static SearchView searchView;
    ListView listView;
    private static AccommodationListViewAdapter adapter;

    private SearchView.OnClickListener onClickListenerSearch = new SearchView.OnClickListener() {
        @Override
        public void onClick(View view) {
            //switch (view.getId()) {
                //case R.id.searchField:
                    searchView.onActionViewExpanded();
             //       break;
            //}
        }
    };

    private ImageButton.OnClickListener onClickListener = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO Start advanced search activity
            Intent intent = new Intent(MainSearchActivity.this, FavoritesActivity.class);
            startActivity(intent);
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    return true;
                case R.id.navigation_favorites:
                    Intent favorites = new Intent(MainSearchActivity.this, FavoritesActivity.class);
                    favorites.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(favorites);
                    return true;
                case R.id.navigation_notifications:
                    Intent searchWatcher = new Intent(MainSearchActivity.this, SearchWatcherActivity.class);
                    searchWatcher.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(searchWatcher);
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ImageButton advancedSearch = (ImageButton) findViewById(R.id.advancedSearch);
        advancedSearch.setOnClickListener(onClickListener);
        //TODO fix searchView
        //searchView = (SearchView) findViewById(R.id.searchField);
        //searchView.setOnClickListener(onClickListenerSearch);


        adapter= new AccommodationListViewAdapter(MainModel.getAccommodations(),getApplicationContext());

        listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Accommodation dataModel= MainModel.getAccommodations().get(position);

                Context context = view.getContext();
                Intent intent = new Intent(context, ObjectActivity.class);
                intent.putExtra("ARG_POSITION", position);

                startActivity(intent);


                //When tapping on a household object
                Snackbar.make(view, dataModel.getAddress(), Snackbar.LENGTH_LONG).setAction("No action", null).show();
            }
        });

    }

}
