package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Db4oDatabase;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.ViewCreationObserver;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.ModalView;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.ActivitySwitcher;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.Search;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchHandler;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherItemView;

/**
 * @author Peter Gärdenäs
 * Revised by: Erik Magnusson
 * Used by: SearchWatcherController
 * Uses: ViewCreationObserver, ModalView, ActvitySwircher, Search, SearchHandler, SearchWatcherItem,
 * SearchWatcerAdapter, SearchWatcherItemView
 * Responsibility: controller for each invduall SearchWatcherItem.
 */

public class SearchWatcherItemController implements ViewCreationObserver{
    private final SearchWatcherItem model;
    private final View view;
    private final Class<? extends  Activity> targetClass;
    private final Activity activity;
    private final SearchWatcherAdapter adapter;

    public SearchWatcherItemController (SearchWatcherItemView view, Activity activity, Class<? extends  Activity> targetClass, SearchWatcherAdapter adapter) {
        this.model = view.getModel();
        this.view = view.getView();
        this.adapter = adapter;
        this.activity = activity;
        this.targetClass = targetClass;
        addListeners();
    }

    private void addListeners() {
        ImageView hamButton = (ImageView) view.findViewById(R.id.hamButton);
        hamButton.setOnClickListener(getEditSearchWatcherListener());

        ImageView searchButton = (ImageView) view.findViewById(R.id.searchWithSearchWatcherButton);
        searchButton.setOnClickListener(getSearchSearchWatcherListener());

        view.setOnClickListener(toggleExpanded());
    }

    private View.OnClickListener toggleExpanded() {
        return (new View.OnClickListener() {
//=======
//        ConstraintLayout layout = (ConstraintLayout) view.findViewById(R.id.row_item);
//        layout.setOnClickListener(new View.OnClickListener() {
//>>>>>>> bra
            @Override
            public void onClick(View v) {
                //TODO Actually implement accordion, this is temp code to test searchwatcher number
                model.getNewMatches().add(Accommodation.getAccommodations().get(0));
                switch (v.getId())
                {
                    case R.id.searchWithSearchWatcherButton:
                        //TODO not used atm
//                AdvancedSearchActivityController.advancedSearchButtonPressed(v);
                        break;
                }
            }
        });
    }

    private ImageView.OnClickListener getEditSearchWatcherListener() {
        return (new ImageView.OnClickListener () {
            @Override
            public void onClick (View view) {
                ModalView.newSearchWatcherModalFragment(activity, SearchWatcherItemController.this, R.id.searchWatcherView);
            }
        });
    }

    private ImageView.OnClickListener getSearchSearchWatcherListener() {
        return (new ImageView.OnClickListener () {
            @Override
            public void onClick (View view) {
                //Remove new searches from searchWatcher
                Db4oDatabase db = Db4oDatabase.getInstance();
                db.deleteAll(SearchWatcherItem.class);
                model.getNewMatches().clear();
                List<SearchWatcherItem> items = SearchWatcherModel.getSearchWatcherItems();
                for (int i = 0; i < items.size(); i++) {
                    db.store(items.get(i));
                }
                db.close();

                //Perform search and move to searchActivity
                Search search = model.getSearch();
                SearchHandler.addToLastSearches(search);
                ActivitySwitcher.getInstance(activity).navigate(targetClass);
            }
        });
    }

    @Override
    public void viewCreated(View view, ModalView modalFragment) {
        new ModalController(view, modalFragment, adapter, model);
    }
}
