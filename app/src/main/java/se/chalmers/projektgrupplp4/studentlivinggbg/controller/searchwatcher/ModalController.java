package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.NameDialog;
import se.chalmers.projektgrupplp4.studentlivinggbg.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher.ModalView;

/**
 * Created by Erik on 2017-05-17.
 */

public class ModalController {
    private Activity activity;
    private ModalView view;
    private final Observer observer;
    private Observer listener;

    public ModalController(Activity activity, ModalView view, Observer observer){
        this.activity = activity;
        this.view = view;
        this.observer = observer;

        initializeToggleModalListener();
        initializeDoNothingListener();
        initializeModalDoneButtonListener();
    }

    void toggle() {
        System.out.println("Toggle");
        if (view.getModalVisibility()) {
            view.getFm().beginTransaction().hide(view.getFm().findFragmentById(R.id.searchWatcherModal)).commit();
        } else {
            view.getFm().beginTransaction().show(view.getFm().findFragmentById(R.id.searchWatcherModal)).commit();
        }
        view.toggleModalVisibility();
        updateModalButton();
    }

    //TODO This does not seem to work
    private void updateModalButton(){
        if(view.getModalVisibility()){
            view.getModalButton().setBackgroundResource(R.drawable.close_icon);
        }else{
            view.getModalButton().setBackgroundResource(R.drawable.plus_icon);
        }
    }

    private void initializeModalDoneButtonListener() {
        ImageButton modalDoneButton = (ImageButton) activity.findViewById(R.id.modalDoneButton);
        modalDoneButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                new NameDialog(activity, listener);
            }
        });
    }

    private void initializeDoNothingListener () {
        //TODO This is super bad code, remove before submit!
        ConstraintLayout searchWatcherContent = (ConstraintLayout) activity.findViewById(R.id.constraintLayout);

        searchWatcherContent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Don't do anything!");
            }
        });
    }

    private void initializeToggleModalListener () {
        ToggleButton toggleButton = (ToggleButton) activity.findViewById(R.id.modalButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toggle();
            }
        });

        ConstraintLayout searchWatcherBackground = (ConstraintLayout) activity.findViewById(R.id.backgroundModal);
        searchWatcherBackground.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.backgroundModal) toggle();
            }
        });
    }
}
