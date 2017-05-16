package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.BottomNavigationListener;
import se.chalmers.projektgrupplp4.studentlivinggbg.RecyclerViewHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.SorterHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.imagemodel.ImageModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.SearchActivityView;

/**
 * Created by PG on 28/04/2017.
 */

public class SearchActivityController {
    private static SearchActivityController controller;

    private Activity activity;
    private SearchView searchView;
    private SearchActivityView activityView;
    private AccommodationRecyclerViewAdapter recyclerAdapter;
    private Spinner sort;

    public SearchActivityController(Activity activity, SearchActivityView activityView, AccommodationRecyclerViewAdapter adapter) {
        this.activity = activity;
        this.activityView = activityView;
        this.recyclerAdapter = adapter;
        RecyclerViewHelper recyclerViewHelper = new RecyclerViewHelper(activity, adapter);
        recyclerViewHelper.initSwipe();
        initListeners();
        controller = this;
    }

    private void initListeners() {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        ImageButton advancedSearch = (ImageButton) activity.findViewById(R.id.advancedSearch);
        searchView = (SearchView) activity.findViewById(R.id.searchField);

        advancedSearch.setOnClickListener(onClickAdvancedSearch);
        navigation.setOnNavigationItemSelectedListener(BottomNavigationListener.getFirstInstance(activity));
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
                        SorterHelper.sortByPrice(accommodations, true);
                        break;
                    case "Pris ↓":
                        SorterHelper.sortByPrice(accommodations, false);
                        break;
                    case "Storlek ↑":
                        SorterHelper.sortBySize(accommodations, true);
                        break;
                    case "Storlek ↓":
                        SorterHelper.sortBySize(accommodations, false);
                        break;
                    case "A-Ö":
                        SorterHelper.sortByAddress(accommodations, false);
                        break;
                    case "Ö-A":
                        SorterHelper.sortByAddress(accommodations, true);
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
            activityView.openAdvancedSearch();
        }
    };

    public static void updateAccommodations(final List<Accommodation> accommodations) {
        if (controller != null) {
            controller.activity.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      ImageModel.getInstance().getAndSaveImages(true, accommodations);
                      Accommodation.setNewAccommodationList(accommodations);
                      controller.recyclerAdapter.refresh();
                  }
              }
            );
        }
    }
}
