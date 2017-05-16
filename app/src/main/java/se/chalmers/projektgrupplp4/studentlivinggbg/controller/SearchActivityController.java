package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.BottomNavigationListener;
import se.chalmers.projektgrupplp4.studentlivinggbg.RecyclerViewHelper;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchActivityModel;
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
    private SearchActivityModel model;
    private Spinner sort;

    public SearchActivityController(Activity activity, SearchActivityModel model, SearchActivityView activityView) {
        this.activity = activity;
        this.model = model;
        this.activityView = activityView;
        RecyclerViewHelper recyclerViewHelper = new RecyclerViewHelper(activity,model);
        recyclerViewHelper.initSwipe();
        initListeners();
        //initSwipe();
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
                "Pris ↑", "Pris ↓",  "Storlek ↑", "Storlek ↓", "A-Ö", "Ö-A",
        };
        sort = (Spinner) activity.findViewById(R.id.sort);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        sort.setAdapter(adapter);
        sort.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) sort.getSelectedItem();
                List<Accommodation> accommodations = model.getRecyclerViewAdapter().getAccommodations();
                switch (selected) {
                    case "Pris ↑":
                        int n = accommodations.size();
                        int k;
                        for (int m = n; m >= 0; m--) {
                            for (int i = 0; i < n - 1; i++) {
                                k = i + 1;

                                if (Double.parseDouble(accommodations.get(i).getPrice()) > Double.parseDouble(accommodations.get(k).getPrice())) {
                                    Accommodation tempK;
                                    tempK = accommodations.get(k);
                                    Accommodation tempI;
                                    tempI = accommodations.get(i);
                                    accommodations.remove(k);
                                    accommodations.remove(i);
                                    accommodations.add(i,tempK);
                                    if (k==n-1) {
                                        accommodations.add(tempI);
                                    } else {
                                        accommodations.add(k, tempI);
                                    }
                                }
                            }
                        }
                        //model.getRecyclerViewAdapter().addAll(accommodations);
                        model.getRecyclerViewAdapter().notifyDataSetChanged();
                        break;
                    case "Pris ↓":
                        n = accommodations.size();
                        k = 0;
                        for (int m = n; m >= 0; m--) {
                            for (int i = 0; i < n - 1; i++) {
                                k = i + 1;

                                if (Double.parseDouble(accommodations.get(i).getPrice()) < Double.parseDouble(accommodations.get(k).getPrice())) {
                                    Accommodation temp;
                                    temp = accommodations.get(k);
                                    accommodations.remove(k);
                                    accommodations.add(k,accommodations.get(i));
                                    accommodations.remove(i);
                                    accommodations.add(i,temp);
                                }
                            }
                        }
                        //model.getRecyclerViewAdapter().clear();
                        //model.getRecyclerViewAdapter().addAll(accommodations);
                        model.getRecyclerViewAdapter().notifyDataSetChanged();
                        break;
                    case "Storlek ↑":
                        n = accommodations.size();
                        k = 0;
                        for (int m = n; m >= 0; m--) {
                            for (int i = 0; i < n - 1; i++) {
                                k = i + 1;

                                if (Double.parseDouble(accommodations.get(i).getArea()) > Double.parseDouble(accommodations.get(k).getArea())) {
                                    Accommodation tempK;
                                    tempK = accommodations.get(k);
                                    Accommodation tempI;
                                    tempI = accommodations.get(i);
                                    accommodations.remove(k);
                                    accommodations.remove(i);
                                    accommodations.add(i,tempK);
                                    if (k==n-1) {
                                        accommodations.add(tempI);
                                    } else {
                                        accommodations.add(k, tempI);
                                    }
                                }
                            }
                        }
                        //model.getRecyclerViewAdapter().addAll(accommodations);
                        model.getRecyclerViewAdapter().notifyDataSetChanged();
                        break;
                    case "Storlek ↓":
                        n = accommodations.size();
                        k = 0;
                        for (int m = n; m >= 0; m--) {
                            for (int i = 0; i < n - 1; i++) {
                                k = i + 1;

                                if (Double.parseDouble(accommodations.get(i).getArea()) < Double.parseDouble(accommodations.get(k).getArea())) {
                                    Accommodation temp;
                                    temp = accommodations.get(k);
                                    accommodations.remove(k);
                                    accommodations.add(k,accommodations.get(i));
                                    accommodations.remove(i);
                                    accommodations.add(i,temp);
                                }
                            }
                        }
                        //model.getRecyclerViewAdapter().clear();
                        //model.getRecyclerViewAdapter().addAll(accommodations);
                        model.getRecyclerViewAdapter().notifyDataSetChanged();
                        break;
                    case "A-Ö":
                        n = accommodations.size();
                        k = 0;
                        for (int m = n; m >= 0; m--) {
                            for (int i = 0; i < n - 1; i++) {
                                k = i + 1;

                                if (accommodations.get(i).getAddress().compareTo(accommodations.get(k).getAddress()) > 0) {
                                    Accommodation temp;
                                    temp = accommodations.get(k);
                                    accommodations.remove(k);
                                    accommodations.add(k,accommodations.get(i));
                                    accommodations.remove(i);
                                    accommodations.add(i,temp);
                                }
                            }
                        }
                        //model.getRecyclerViewAdapter().clear();
                        //model.getRecyclerViewAdapter().addAll(accommodations);
                        model.getRecyclerViewAdapter().notifyDataSetChanged();
                        break;
                    case "Ö-A":
                        n = accommodations.size();
                        k = 0;
                        for (int m = n; m >= 0; m--) {
                            for (int i = 0; i < n - 1; i++) {
                                k = i + 1;

                                if (accommodations.get(i).getAddress().compareTo(accommodations.get(k).getAddress()) < 0) {
                                    Accommodation temp;
                                    temp = accommodations.get(k);
                                    accommodations.remove(k);
                                    accommodations.add(k,accommodations.get(i));
                                    accommodations.remove(i);
                                    accommodations.add(i,temp);
                                }
                            }
                        }
                        //model.getRecyclerViewAdapter().clear();
                        //model.getRecyclerViewAdapter().addAll(accommodations);
                        model.getRecyclerViewAdapter().notifyDataSetChanged();
                        break;

                    }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*sort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) sort.getSelectedItem();
                /*switch (selected) {
                    case "Pris ↑":
                        int n = model.getRecyclerView()
                        int k;
                        for (int m = n; m >= 0; m--) {
                            for (int i = 0; i < n - 1; i++) {
                                k = i + 1;
                                if (array[i] > array[k]) {
                                    swapNumbers(i, k, array);
                                }
                            }
                            printNumbers(array);
                        }
                    }

                    private static void swapNumbers(int i, int j, int[] array) {

                        int temp;
                        temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
            }
        });*/
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
            model.refreshAdapter();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            if(query.length()==0){
                SearchHandler.createSearch("", true);
                model.refreshAdapter();
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
                      controller.model.refreshAdapter();
                  }
              }
            );
        }
    }


}
