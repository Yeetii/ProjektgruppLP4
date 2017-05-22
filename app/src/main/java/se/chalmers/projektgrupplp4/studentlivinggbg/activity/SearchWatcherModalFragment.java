package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher.ModalController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.ModalView;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherAdapter;

/**
 * Created by Erik on 2017-05-19.
 */

public class SearchWatcherModalFragment extends Fragment {

    private SearchWatcherAdapter adapter;
    private SearchWatcherItem model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_watcher_modal, container, false);
        ModalView modalView = new ModalView(view);
        new ModalController(view, modalView, this, adapter, model);
        // Inflate the layout for this fragment
        return view;
    }

    public void setAdapter(SearchWatcherAdapter adapter){
        this.adapter = adapter;
    }

    public void setModel(SearchWatcherItem model) {
        this.model = model;
    }

    //Using constructor methods because I wasn't able to create the controller and view outside the fragment. And those need some extra parameters.
    public static FragmentManager newSearchWatcherModalFragment(Activity activity, SearchWatcherAdapter adapter, int viewID){
        return newSearchWatcherModalFragment(activity, adapter, viewID, null);
    }

    public static FragmentManager newSearchWatcherModalFragment(Activity activity, SearchWatcherAdapter adapter, int viewID, SearchWatcherItem searchWatcherItem){
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SearchWatcherModalFragment fragment = new SearchWatcherModalFragment();
        fragment.setAdapter(adapter);
        fragment.setModel(searchWatcherItem);
        fragmentTransaction.add(viewID, fragment);
        //Adds the fragment to the back button history among other things
        fragmentTransaction.addToBackStack("SearchWatcherModal").commit();
        return fragmentManager;
    }

}