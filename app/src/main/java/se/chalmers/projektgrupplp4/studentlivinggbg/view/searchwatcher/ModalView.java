package se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by Erik on 2017-05-19.
 */

public class ModalView extends Fragment{
    private View view;
    private ViewCreationObserver observer;

    private TextView doneText;
    private TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.search_watcher_modal, container, false);
        initReferences();
        observer.viewCreated(view, this);
        return view;
    }

    public void setObserver(ViewCreationObserver observer) {
        this.observer = observer;
    }
    //Using constructor method because it needs more parameters than a standard fragment, maybe not best solution?
    public static ModalView newSearchWatcherModalFragment(Activity activity, ViewCreationObserver observer, int viewID){
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ModalView fragment = new ModalView();
        fragment.setObserver(observer);
        fragmentTransaction.add(viewID, fragment);
        //Adds the fragment to the back button history among other things
        fragmentTransaction.addToBackStack("SearchWatcherModal").commit();
        return fragment;
    }

    private void initReferences() {
        doneText = (TextView) view.findViewById(R.id.modalDoneText);
        title = (TextView) view.findViewById(R.id.modalTitle);
    }

    public void update(boolean newMode){
        if (newMode){
            newMode();
        }else{
            editMode();
        }
    }

    private void editMode(){
        doneText.setText("Spara");
        title.setText("Redigera bevakning");
    }
    private void newMode(){
        doneText.setText("Skapa");
        title.setText("Skapa bevakning");
    }
}