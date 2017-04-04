package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PG on 03/04/2017.
 */

public class SearchWatcherActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_watcher);
        Log.d("Test", "Activity created!");
        ListView listView=(ListView)findViewById(R.id.listView);

        List<SearchWatcherItem> your_array_list = new ArrayList<SearchWatcherItem>();
        your_array_list.add(new SearchWatcherItem("temp1"));
        your_array_list.add(new SearchWatcherItem("temp2"));
        your_array_list.add(new SearchWatcherItem("temp3"));
        your_array_list.add(new SearchWatcherItem("temp4"));
        your_array_list.add(new SearchWatcherItem("temp5"));


        fm = getFragmentManager();
        ArrayAdapter<SearchWatcherItem> arrayAdapter = new SearchWatcherItemAdapter(this, your_array_list);

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


        listView.setAdapter(arrayAdapter);
    }
    private static FragmentManager fm;
    private static boolean isModalVisible = true;


    public static void toggle () {
        System.out.println("Clicked toggle");
        if (isModalVisible) {
            fm.beginTransaction()
                    .hide(fm.findFragmentById(R.id.searchWatcherModal))
                    .commit();
        } else {
            System.out.println("Show");
            fm.beginTransaction()
                    .show(fm.findFragmentById(R.id.searchWatcherModal))
                    .commit();
        }
        isModalVisible = !isModalVisible;
        System.out.println(isModalVisible);

    }
}
