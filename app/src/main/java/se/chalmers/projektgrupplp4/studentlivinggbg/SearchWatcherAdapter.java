package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.AdvancedSearchActivityController;
import se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher.SearchWatcherItemController;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchWatcher.SearchWatcherItemView;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;


/**
 * Created by PG on 03/04/2017.
 */

public class SearchWatcherAdapter extends ArrayAdapter<SearchWatcherItem> implements View.OnClickListener{

    public SearchWatcherAdapter(Context context, List<SearchWatcherItem> data) {
        super(context, R.layout.search_watcher_row_item, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SearchWatcherItem dataModel = getItem(position);
        SearchWatcherItemView viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.search_watcher_row_item, parent, false);
            viewHolder = new SearchWatcherItemView(dataModel, convertView);

            new SearchWatcherItemController(dataModel, convertView);

        }else{
            viewHolder = (SearchWatcherItemView) convertView.getTag();
        }

        viewHolder.updateView();
        return convertView;
    }

    //When tapping on a SearchWatcherItem
    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        SearchWatcherItem tappedSearch = getItem(position);


        switch (v.getId())
        {
            case R.id.searchWithSearchWatcherButton:
                AdvancedSearchActivityController.advancedSearchButtonPressed(v);
                break;
        }
    }
}

















