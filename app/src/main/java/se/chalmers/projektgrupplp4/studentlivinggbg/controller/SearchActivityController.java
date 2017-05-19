package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.service.ActivitySwitcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.RecyclerViewHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.AccommodationsSorter;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;

/**
 * Created by PG on 28/04/2017.
 */

public class SearchActivityController {
    private static SearchActivityController controller;

    private Activity activity;
    private SearchView searchView;
    private AccommodationRecyclerViewAdapter recyclerAdapter;
    private Spinner sort;
    private Class<? extends Activity> targetActivity;

    public SearchActivityController(Activity activity, AccommodationRecyclerViewAdapter adapter, Class<? extends Activity> targetActivity) {
        this.activity = activity;
        this.recyclerAdapter = adapter;
        this.targetActivity = targetActivity;
        RecyclerViewHelper recyclerViewHelper = new RecyclerViewHelper(activity, adapter);
        recyclerViewHelper.initSwipe();
        initListeners();
        controller = this;
    }

    private void initListeners() {
        ImageButton advancedSearch = (ImageButton) activity.findViewById(R.id.advancedSearch);
        searchView = (SearchView) activity.findViewById(R.id.searchField);

        advancedSearch.setOnClickListener(onClickAdvancedSearch);
        searchView.setIconifiedByDefault(true);
        searchView.setOnClickListener(onClickListener);
        searchView.setOnQueryTextListener(onQueryTextListener);

        final String[] arraySpinner = new String[] {
                "Pris ↓", "Pris ↑",  "Storlek ↓", "Storlek ↑", "A-Ö", "Ö-A",
        };
        sort = (Spinner) activity.findViewById(R.id.sort);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        sort.setAdapter(adapter);
        sort.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) sort.getSelectedItem();
                List<Accommodation> accommodations = recyclerAdapter.getAccommodations();
                switch (selected) {
                    case "Pris ↑":
                        AccommodationsSorter.sortByPrice(accommodations, true);
                        break;
                    case "Pris ↓":
                        AccommodationsSorter.sortByPrice(accommodations, false);
                        break;
                    case "Storlek ↑":
                        AccommodationsSorter.sortBySize(accommodations, true);
                        break;
                    case "Storlek ↓":
                        AccommodationsSorter.sortBySize(accommodations, false);
                        break;
                    case "A-Ö":
                        AccommodationsSorter.sortByAddress(accommodations, false);
                        break;
                    case "Ö-A":
                        AccommodationsSorter.sortByAddress(accommodations, true);
                        break;
                    }
                recyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private SearchView.OnClickListener onClickListener = new SearchView.OnClickListener() {
        @Override
        public void onClick(View v) {
            searchView.setIconified(false);
        }
    };




    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener(){

        @Override
        public boolean onQueryTextSubmit(String query) {
            SearchHandler.createSearch(query);
            controller.recyclerAdapter.refresh();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            if(query.length()==0){
                SearchHandler.createSearch("", true);
                controller.recyclerAdapter.refresh();
            }
            return false;
        }
    };

    //Advanced search button
    private ImageButton.OnClickListener onClickAdvancedSearch = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            ActivitySwitcher.getInstance(activity).navigate(targetActivity);
        }
    };

    public static void updateAccommodations(final List<Accommodation> accommodations) {
        if (controller != null) {
            controller.activity.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      AccommodationsSorter.sortByPrice(accommodations, false);
                      Accommodation.setNewAccommodationList(accommodations);
                      controller.recyclerAdapter.refresh();
                  }
              }
            );
        }
    }
}
