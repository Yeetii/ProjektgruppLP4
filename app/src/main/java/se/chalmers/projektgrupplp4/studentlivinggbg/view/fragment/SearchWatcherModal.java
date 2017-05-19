package se.chalmers.projektgrupplp4.studentlivinggbg.view.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

public class SearchWatcherModal extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_watcher_modal, container, false);
        return view;
    }
}
