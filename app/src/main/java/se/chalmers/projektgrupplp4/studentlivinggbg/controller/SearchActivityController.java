package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.SettingsModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.ActivitySwitcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.AccommodationsSorter;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchList;

/**
 * @author Peter Gärdenäs
 * revised by John Segerstedt, Jonathan Gildevall
 * Used by: MainSearchActivty, ActivityReceiver
 * Uses: SettingsModel, ActivitySwircher, Db4oDatabase, AccommodationRecyclerViewAdapter, AccommodationsSorter,
 * Accommodation, SearchList, RecyclerViewHelperController.
 * Responsibilty: Contoller for the MainSearchActivity (and since MainSearchActivity is kind of the main activity
 * this becomes partly the main controller).
 */

public class SearchActivityController {
    private static SearchActivityController controller;
    private static String selectedSorter;

    private final Activity activity;
    private SearchView searchView;
    private final AccommodationRecyclerViewAdapter recyclerAdapter;
    private Spinner sort;
    private final Class<? extends Activity> targetActivity;
    private final String[] arraySpinner = new String[] {
            "Pris ↓", "Pris ↑",  "Storlek ↓", "Storlek ↑", "A-Ö", "Ö-A",
    };

    public SearchActivityController(Activity activity, AccommodationRecyclerViewAdapter adapter, Class<? extends Activity> targetActivity) {
        this.activity = activity;
        this.recyclerAdapter = adapter;
        this.targetActivity = targetActivity;
        new RecyclerViewHelperController(activity, adapter);
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
        sort = (Spinner) activity.findViewById(R.id.sort);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        sort.setAdapter(adapter);
        sort.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) sort.getSelectedItem();
                List<Accommodation> accommodations = recyclerAdapter.getAccommodations(); //To get instant result
                List<Accommodation> allAccommodations = Accommodation.getAccommodations(); //To change in other views
                switch (selected) {
                    case "Pris ↑":
                        AccommodationsSorter.sortByPrice(accommodations, true);
                        AccommodationsSorter.sortByPrice(allAccommodations, true);
                        break;
                    case "Pris ↓":
                        AccommodationsSorter.sortByPrice(accommodations, false);
                        AccommodationsSorter.sortByPrice(allAccommodations, false);
                        break;
                    case "Storlek ↑":
                        AccommodationsSorter.sortBySize(accommodations, true);
                        AccommodationsSorter.sortBySize(allAccommodations, true);
                        break;
                    case "Storlek ↓":
                        AccommodationsSorter.sortBySize(accommodations, false);
                        AccommodationsSorter.sortBySize(allAccommodations, false);
                        break;
                    case "A-Ö":
                        AccommodationsSorter.sortByAddress(accommodations, false);
                        AccommodationsSorter.sortByAddress(allAccommodations, false);
                        break;
                    case "Ö-A":
                        AccommodationsSorter.sortByAddress(accommodations, true);
                        AccommodationsSorter.sortByAddress(allAccommodations, true);
                        break;
                    }
                selectedSorter = selected;
                recyclerAdapter.notifyDataSetChanged();
                storeDefaultSelector(selected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        selectSorter();
    }

    private void storeDefaultSelector(String selected) {
        SettingsModel model = SettingsModel.getInstance();
        model.setDefaultSort(selected);
        Db4oDatabase db = Db4oDatabase.getInstance();
        db.deleteAll(SettingsModel.class);
        db.store(model);
        db.close();
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
            SearchList.createSearch(query);
            controller.recyclerAdapter.refresh();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            if(query.length()==0){
                SearchList.createSearch("", true);
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

    private void selectSorter() {
        if (selectedSorter == null) selectedSorter = SettingsModel.getInstance().getDefaultSort();
        for (int i = 0; i < arraySpinner.length; i++) {
            if (selectedSorter.equals(arraySpinner[i])) {
                sort.setSelection(i);
                break;
            }
        }
    }

    public static void updateAccommodations(final List<Accommodation> accommodations) {
        if (controller != null) {
            controller.activity.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      Accommodation.setNewAccommodationList(accommodations);
                      controller.selectSorter();
                      controller.recyclerAdapter.refresh();
                  }
              }
            );
        }
    }
}
