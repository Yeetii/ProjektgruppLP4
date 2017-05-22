package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Fragment;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.NameDialog;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.AdvancedSearchFragmentController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.ModalView;

/**
 * Created by Erik on 2017-05-17.
 */

public class ModalController implements Observer {
    private final AdvancedSearchFragmentController advancedSearchFragmentController;
    private final Fragment fragment;
    //    private Fragment fragment;
    private ModalView modalView;
    private View view;
    private SearchWatcherAdapter adapter;
//    private final Observer observer;
    private Observer listener;

    //Adapter needed to notofiy listView when a new searchWatcher is created
    public ModalController(View view, ModalView modalView, Fragment fragment, SearchWatcherAdapter adapter){
//        this.fragment = fragment;
        this.view = view;
        this.modalView = modalView;
        this.fragment = fragment;
        this.adapter = adapter;
//        this.observer = observer;

        this.advancedSearchFragmentController = new AdvancedSearchFragmentController(view);

        initializeCloseModalListener();
        initializeDoNothingListener();
        initializeModalDoneButtonListener();
    }

    void close() {
        System.out.println("Close");

        fragment.getActivity().getFragmentManager().beginTransaction().remove(fragment).commit();

        //OLD CODE MOVE TO OTHER PLACES

        if (modalView.getModalVisibility()) {
//            modalView.getFm().beginTransaction().hide(modalView.getFm().findFragmentById(R.id.searchWatcherModal)).commit();
        } else {
//            modalView.getFm().beginTransaction().show(modalView.getFm().findFragmentById(R.id.searchWatcherModal)).commit();
            if (!modalView.getNewMode())
                advancedSearchFragmentController.fillFilters(modalView.getModel().getSearch());
        }
        modalView.toggleModalVisibility();
    }

    private void initializeModalDoneButtonListener() {
        ImageButton modalDoneButton = (ImageButton) view.findViewById(R.id.modalDoneButton);
        modalDoneButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                new NameDialog(view, ModalController.this);
            }
        });
    }

    private void initializeDoNothingListener () {
        //TODO This is super bad code, remove before submit!
        ConstraintLayout searchWatcherContent = (ConstraintLayout) view.findViewById(R.id.constraintLayout);

        searchWatcherContent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Don't do anything!");
            }
        });
    }

    private void initializeCloseModalListener() {
        ImageButton closeButton = (ImageButton) view.findViewById(R.id.modalCloseButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                close();
            }
        });

        ConstraintLayout searchWatcherBackground = (ConstraintLayout) view.findViewById(R.id.backgroundModal);
        searchWatcherBackground.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.backgroundModal) close();
            }
        });
    }

    public Search parseSearchTerms(boolean addToSearchHistory) {
        return advancedSearchFragmentController.parseSearchTerms(addToSearchHistory);
    }

    //Called by the NameDialog
    @Override
    public void update(String updateString) {
        Search search = parseSearchTerms(false);
        SearchWatcherModel.createSearchWatcher(updateString, search);
        adapter.notifyDataSetChanged();
//        adapter.refresh();
        close();
    }
}
