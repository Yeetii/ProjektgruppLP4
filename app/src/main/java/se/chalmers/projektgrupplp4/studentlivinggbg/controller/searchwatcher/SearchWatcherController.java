package se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher;

import android.app.Activity;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherItemView;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.SearchWatcherModalFragment;

/**
 * Created by PG on 21/04/2017.
 */

public class SearchWatcherController implements Observer{
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
                SearchWatcherModalFragment.newSearchWatcherModalFragment(activity, adapter, R.id.searchWatcherView);
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
}
