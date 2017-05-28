package se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.service.Observer;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.searchwatcher.SearchWatcher;


/**
 * @author Peter Gärdenäs
 * Used by: ModalController, SearchWatcherActivity, SearchWatcherController, SearchWatcherItemController, SearchWatcherView
 * Uses: SearchWatcher, Observer, search_watcher_row_item
 * Responsibility: inflate the advanced search fragment
 */

public class SearchWatcherAdapter extends ArrayAdapter<SearchWatcher> {

    private final LayoutInflater inflater;
//    private final Class<? extends Activity> targetClass;
    private final Observer observer;
    private List<SearchWatcherItemView> views = new ArrayList<>();


    public SearchWatcherAdapter(Context context, List<SearchWatcher> data, Observer observer) {
        super(context, R.layout.search_watcher_row_item, data);
        this.observer = observer;
//        this.targetClass = targetClass;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SearchWatcher dataModel = getItem(position);
        SearchWatcherItemView viewHolder;
        System.out.println("Making list item " + position + " with title" + dataModel.getTitle());

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.search_watcher_row_item, null);
            viewHolder = new SearchWatcherItemView(dataModel, convertView);
            views.add(viewHolder);
            observer.update(null);
        }else{
            viewHolder = (SearchWatcherItemView) convertView.getTag();
        }

        viewHolder.updateView(dataModel);
        return convertView;
    }


    public List<SearchWatcherItemView> getViews() {
        return views;
    }
}