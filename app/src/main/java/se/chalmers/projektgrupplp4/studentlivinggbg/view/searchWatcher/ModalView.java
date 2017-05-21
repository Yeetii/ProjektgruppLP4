package se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher;

import android.app.Fragment;
import android.app.FragmentManager;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;

/**
 * Created by Erik on 2017-05-17.
 */

public class ModalView {
    private View view;
    private ToggleButton modalButton;
    private boolean modalVisibility = false;
    private boolean newMode;
    private SearchWatcherItem model;

    private TextView doneText;
    private TextView title;

    private ModalView (View view, boolean newMode){
        this.view = view;
        this.newMode = true;

        initReferences();
    }

    public ModalView (View view){
        this(view, true);
    }

    public ModalView(View view, SearchWatcherItem model){
        this(view, false);
        this.model = model;
    }

    private void initReferences() {
        doneText = (TextView) view.findViewById(R.id.modalDoneText);
        title = (TextView) view.findViewById(R.id.modalTitle);
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
//        fm.beginTransaction().hide(fm.findFragmentById(R.id.searchWatcherModal)).commit();
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

    public SearchWatcherItem getModel() {
        return model;
    }
    public boolean getNewMode(){
        return newMode;
    }
}
