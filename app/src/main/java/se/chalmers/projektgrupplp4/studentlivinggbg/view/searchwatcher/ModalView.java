package se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher;

import android.view.View;
import android.widget.TextView;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by Erik on 2017-05-17.
 */

public class ModalView {
    private View view;
    private TextView doneText;
    private TextView title;

    public ModalView (View view){
        this.view = view;

        initReferences();
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
