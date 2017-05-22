package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Fragment;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.NameDialog;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.AdvancedSearchFragmentController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.ModalView;

/**
 * Created by Erik on 2017-05-17.
 */

public class ModalController implements Observer {
    private final AdvancedSearchFragmentController advancedSearchFragmentController;
    private final Fragment fragment;
    private ModalView modalView;
    private View view;
    private SearchWatcherAdapter adapter;
    private SearchWatcherItem model;

    //Adapter needed to notify listView when a new searchWatcher is created
    public ModalController(View view, ModalView modalView, Fragment fragment, SearchWatcherAdapter adapter, SearchWatcherItem model){
        this.view = view;
        this.modalView = modalView;
        this.fragment = fragment;
        this.adapter = adapter;
        this.model = model;

        this.advancedSearchFragmentController = new AdvancedSearchFragmentController(view);

        initializeCloseModalListener();
        initializeDoNothingListener();
        initializeModalDoneButtonListener();

        if (editMode()){
            System.out.println("FIlling from modal with real studd" + model.getSearch().getMaxPrice());
            advancedSearchFragmentController.fillFilters(model.getSearch());
            modalView.update(!editMode());
        }
    }

    public void close() {
        fragment.getActivity().getFragmentManager().beginTransaction().remove(fragment).commit();
    }

    private void initializeModalDoneButtonListener() {
        ImageButton modalDoneButton = (ImageButton) view.findViewById(R.id.modalDoneButton);
        modalDoneButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (editMode()){
                    model.setSearch(parseSearchTerms());
                    close();
                }else {
                    new NameDialog(view, ModalController.this);
                }
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

    public Search parseSearchTerms() {
        return advancedSearchFragmentController.parseSearchTerms(false);
    }

    //Called by the NameDialog
    @Override
    public void update(String updateString) {
        Search search = parseSearchTerms();
        SearchWatcherModel.createSearchWatcher(updateString, search);
        adapter.notifyDataSetChanged();
        close();
    }

    public boolean editMode(){
        return model != null;
    }
}
