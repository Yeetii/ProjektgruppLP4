package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;
import se.chalmers.projektgrupplp4.studentlivinggbg.Controller.MainController;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.MainModel;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;
import static se.chalmers.projektgrupplp4.studentlivinggbg.R.id.searchField;

public class MainSearchActivity extends AppCompatActivity {

    private SearchView searchView;
    ListView listView;
    private AccommodationListViewAdapter adapter;

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

    //Advanced search button
    private ImageButton.OnClickListener onClickListener = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainSearchActivity.this, AdvancedSearchActivity.class);
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
        new MainController(getApplicationContext());
        setContentView(R.layout.activity_main_search);

        adapter= new AccommodationListViewAdapter(new ArrayList<Accommodation>(),getApplicationContext());

        listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Accommodation dataModel= MainModel.getInstance().getAccommodations().get(position);

                Context context = view.getContext();
                Intent intent = new Intent(context, ObjectActivity.class);
                intent.putExtra("ARG_POSITION", position);

                startActivity(intent);

                //When tapping on a household object
                Snackbar.make(view, dataModel.getAddress(), Snackbar.LENGTH_LONG).setAction("No action", null).show();
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ImageButton advancedSearch = (ImageButton) findViewById(R.id.advancedSearch);
        advancedSearch.setOnClickListener(onClickListener);
        searchView = (SearchView) findViewById(R.id.searchField);
        searchView.setOnClickListener(onClickListenerSearch);
        try {
            /*
            Wanted to use observer pattern but: "Only the original thread that created a view
            hierarchy can touch its views" And loading/creating the database seems to require
            threading.
            */

            MainModel.dbThread.join();
            displaySearch();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void displaySearch() {
        adapter.clear();
        adapter.addAll(MainModel.getInstance().getAccommodations());
    }

    @Override
    protected void onPause() {
        /*
        Throws errors currently and since it is only a temp solution I won't fix it now.
        MainModel.getInstance().save();
        System.out.println("Saving!");
        Db4oDatabase.getInstance().close();
        */
        super.onPause();

    }

}
