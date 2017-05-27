package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Activity;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.ViewCreationObserver;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherItemView;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.ModalView;

/**
 * @author Peter
 */

public class SearchWatcherController implements Observer, ViewCreationObserver{
    private Class<? extends Activity> targetClass;
    private final Activity activity;
    private SearchWatcherAdapter adapter;

    public SearchWatcherController(Activity activity,  Class<? extends Activity> targetClass) {
        this.activity = activity;
        this.targetClass = targetClass;
        initializeListeners();
    }

    private void initializeListeners() {
        initializeNavigationListener();
        initializeNewSWListener();
    }

    private void initializeNavigationListener () {
        BottomNavigationView navigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        System.out.println(navigation);
        //TODO SOMETHING
//        navigation.setOnNavigationItemSelectedListener(BottomNavigationListener.getInstance());
        navigation.setSelectedItemId(R.id.navigation_notifications);
    }

    private void initializeNewSWListener(){
        ImageButton newSWButton = (ImageButton) activity.findViewById(R.id.newSearchWatcherButton);
        newSWButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Controller for the modal is created via observer callback in viewCreated because the view hasn't been created at this point
                ModalView.newSearchWatcherModalFragment(activity, SearchWatcherController.this, R.id.searchWatcherView);
            }
        });
    }

    @Override
    public void update(String updateString) {
        if (adapter == null) return;
        List<SearchWatcherItemView> views = adapter.getViews();
        for (int i = 0; i < views.size(); i++) {
            SearchWatcherItemView view = views.get(i);
            if (!view.isControllerAttached()) {
                new SearchWatcherItemController(view, activity, targetClass, adapter);
            }
        }
    }

    public void setAdapter(SearchWatcherAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void viewCreated(View view, ModalView modalFragment) {
        new ModalController(view, modalFragment, adapter, null);
    }
}
