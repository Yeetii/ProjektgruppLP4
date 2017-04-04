package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity {

    ArrayList<Accommodation> dataModels;
    ListView listView;
    private static AccommodationListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new Intent(getApplicationContext(), SearchWatcherActivity.class);
                startActivity(nextScreen);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
