package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.AlertDialog;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.NameDialogController;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.NameDialog;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.ModalView;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.AdvancedSearchFragmentController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;

/**
 * Created by Erik on 2017-05-17.
 */

public class ModalController implements Observer {
    private final AdvancedSearchFragmentController advancedSearchFragmentController;
    private final ModalView fragment;
    private final View view;
    private final SearchWatcherAdapter adapter;
    private final SearchWatcherItem model;

    //Adapter needed to notify listView when a new searchWatcher is created
    public ModalController(View view, ModalView fragment, SearchWatcherAdapter adapter, SearchWatcherItem model){
        this.view = view;
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
            fragment.update(!editMode());
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
                    //Not an optimal solution but it might be tricky to write a safe delete method.
                    Db4oDatabase db = Db4oDatabase.getInstance();
                    db.deleteAll(SearchWatcherItem.class);
                    model.setSearch(parseSearchTerms());
                    List<SearchWatcherItem> items = SearchWatcherModel.getSearchWatcherItems();
                    for (int i = 0; i < items.size(); i++) {
                        db.store(items.get(i));
                    }
                    db.close();
                    close();
                }else {
                    createNameDialog();
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
        SearchWatcherItem sw = SearchWatcherModel.createSearchWatcher(updateString, search);
        Db4oDatabase.getInstance().store(sw);
        Db4oDatabase.getInstance().close();
        adapter.notifyDataSetChanged();
        close();
    }

    public boolean editMode(){
        return model != null;
    }

    private void createNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
        NameDialog nameDialog = new se.chalmers.projektgrupplp4.studentlivinggbg.view.NameDialog(builder, fragment.getActivity());
        new NameDialogController(builder, nameDialog, this);
    }
}
