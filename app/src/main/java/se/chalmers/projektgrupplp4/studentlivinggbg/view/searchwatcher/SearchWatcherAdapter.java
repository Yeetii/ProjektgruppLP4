package se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;


/**
 * Created by PG on 03/04/2017.
 */

public class SearchWatcherAdapter extends ArrayAdapter<SearchWatcherItem> {

    private final LayoutInflater inflater;
//    private final Class<? extends Activity> targetClass;
    private final Observer observer;
    private List<SearchWatcherItemView> views = new ArrayList<>();


    public SearchWatcherAdapter(Context context, List<SearchWatcherItem> data, Observer observer) {
        super(context, R.layout.search_watcher_row_item, data);
        this.observer = observer;
//        this.targetClass = targetClass;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SearchWatcherItem dataModel = getItem(position);
        SearchWatcherItemView viewHolder;
        System.out.println("Making list item " + position + " with title" + dataModel.getTitle());

        if (convertView == null) {
            if (dataModel.isExpanded()) {
                convertView = inflater.inflate(R.layout.search_watcher_row_item_expanded, null);
            } else {
                convertView = inflater.inflate(R.layout.search_watcher_row_item, null);
            }
            viewHolder = new SearchWatcherItemView(dataModel, convertView);
            views.add(viewHolder);
            observer.update(null);
        }else{
            viewHolder = (SearchWatcherItemView) convertView.getTag();
        }

        viewHolder.updateView(dataModel);
        return convertView;
    }

    //When tapping on a SearchWatcherItem
    /*
    This should be done in the controller imo.
    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        SearchWatcherItem tappedSearch = getItem(position);
        //TODO Access the controller?

        if (tappedSearch.isExpanded()) {
            tappedSearch.resetExpanded();
        } else {
            tappedSearch.setExpanded();
        }

        switch (v.getId())
        {
            case R.id.searchWithSearchWatcherButton:
                //TODO not used atm
//                AdvancedSearchActivityController.advancedSearchButtonPressed(v);
                break;
        }
    }
    */
    public List<SearchWatcherItemView> getViews() {
        return views;
    }
}