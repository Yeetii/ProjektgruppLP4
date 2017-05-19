package se.chalmers.projektgrupplp4.studentlivinggbg.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

public class AdvancedSearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.advanced_search_fragment, container, false);
        return inflatedView;
    }
}
