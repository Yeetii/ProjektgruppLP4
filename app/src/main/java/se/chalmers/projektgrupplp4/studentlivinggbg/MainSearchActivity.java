package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

public class MainSearchActivity extends AppCompatActivity {

    private static SearchView searchView;
    ArrayList<Accommodation> dataModels;
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

        listView=(ListView)findViewById(R.id.list);
        //Examples of list objects
        dataModels= new ArrayList<>();
        dataModels.add(new Accommodation("Lindholmsallén 37 Läg 101", AccommodationHouseType.TVÅ_RUM, 3650, 16.4, 120, R.drawable.house_image1));
        dataModels.add(new Accommodation("ViktorRydbersgatan 48 Läg 1208", AccommodationHouseType.KORRIDORSRUM, 3650, 40, 0, R.drawable.house_image2));
        dataModels.add(new Accommodation("Våxtorpsgatan 00 Läg 1337", AccommodationHouseType.FYRA_RUM, 12345, 99, 1200, R.drawable.house_image3));

        adapter= new AccommodationListViewAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Accommodation dataModel= dataModels.get(position);

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
