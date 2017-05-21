package se.chalmers.projektgrupplp4.studentlivinggbg.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher.ModalController;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher.ModalView;

/**
 * Created by Erik on 2017-05-19.
 */

public class SearchWatcherModalFragment extends Fragment {

    private SearchWatcherAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_watcher_modal, container, false);
        ModalView modalView = new ModalView(view);
        new ModalController(view, modalView, this, adapter);
        // Inflate the layout for this fragment
        return view;
    }

    public void setAdapter(SearchWatcherAdapter adapter){
        this.adapter = adapter;
    }
}