package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;

import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.controller.searchwatcher.SearchWatcherItemController;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.SearchWatcherItemView;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcherItem;


/**
 * Created by PG on 03/04/2017.
 */

public class SearchWatcherAdapter extends ArrayAdapter<SearchWatcherItem> implements View.OnClickListener{

    private Activity activity;
    private List<SearchWatcherItem> data;
    private LayoutInflater inflater;
    Class<? extends Activity> targetClass;


    public SearchWatcherAdapter(Context context, List<SearchWatcherItem> data, Activity activity, Class<? extends Activity> targetClass) {
        super(context, R.layout.search_watcher_row_item, data);
        this.data = data;
        this.targetClass = targetClass;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SearchWatcherItem dataModel = getItem(position);
        SearchWatcherItemView viewHolder;
        System.out.println("Making list item " + position + " with title" + dataModel.getTitle());

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.search_watcher_row_item, null);
            viewHolder = new SearchWatcherItemView(dataModel, convertView);
            new SearchWatcherItemController(dataModel, convertView, activity, targetClass);


        }else{
            viewHolder = (SearchWatcherItemView) convertView.getTag();
        }

        viewHolder.updateView(dataModel);
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
                //TODO not used atm
//                AdvancedSearchActivityController.advancedSearchButtonPressed(v);
                break;
        }
    }

    public void refresh() {
        data.clear();
        data.addAll(SearchWatcherModel.getSearchWatcherItems());
    }

    @Override
    public long getItemId(int position){
            return super.getItemId(position);
    }
}