package se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher;

import android.app.Activity;
import android.app.FragmentManager;
import android.widget.TextView;
import android.widget.ToggleButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;

/**
 * Created by Erik on 2017-05-17.
 */

public class ModalView {
    private Activity activity;
    private ToggleButton modalButton;
    private FragmentManager fm;
    private boolean modalVisibility = false;
    private boolean newMode;
    private SearchWatcherItem model;

    private TextView doneText;
    private TextView title;

    public ModalView (Activity activity, boolean newMode){
        this.activity = activity;
        this.newMode = newMode;
        this.fm = activity.getFragmentManager();
        this.modalButton = new ToggleButton(activity);

        initReferences();
    }

    public ModalView(Activity activity, SearchWatcherItem model){
        this(activity, false);
        this.model = model;
    }

    private void initReferences() {
        doneText = (TextView) activity.findViewById(R.id.modalDoneText);
        title = (TextView) activity.findViewById(R.id.modalTitle);
    }

    public void update(){
        System.out.println(newMode);
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
        update();
    }

    public FragmentManager getFm() {
        return fm;
    }

    public SearchWatcherItem getModel() {
        return model;
    }
    public boolean getNewMode(){
        return newMode;
    }
}
