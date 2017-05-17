package se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher;

import android.app.Activity;
import android.app.FragmentManager;
import android.widget.ToggleButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by Erik on 2017-05-17.
 */

public class ModalView {
    private ToggleButton modalButton;
    private FragmentManager fm;
    private boolean modalVisibility = false;

    public ModalView (Activity activity){
        this.fm = activity.getFragmentManager();
        this.modalButton = new ToggleButton(activity);

    }


    public void hideModal () {
        fm.beginTransaction().hide(fm.findFragmentById(R.id.searchWatcherModal)).commit();
        modalVisibility = false;
    }

    public ToggleButton getModalButton() {
        return modalButton;
    }

    public boolean getModalVisibility() {
        return modalVisibility;
    }

    public void toggleModalVisibility(){
        modalVisibility = !modalVisibility;
    }

    public FragmentManager getFm() {
        return fm;
    }

}
