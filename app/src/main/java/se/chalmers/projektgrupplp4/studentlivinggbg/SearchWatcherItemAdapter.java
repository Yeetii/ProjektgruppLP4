package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;
import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;


/**
 * Created by PG on 03/04/2017.
 */

public class SearchWatcherItemAdapter extends ArrayAdapter<SearchWatcherItem> implements View.OnClickListener {

    public SearchWatcherItemAdapter(Context context, List<SearchWatcherItem> stringList) {
        super(context, R.layout.search_watcher_row_item, R.id.textViewBottomLeft, stringList);
    }

    private boolean isShowing = true;

    @Override
    public void onClick(View v) {
        System.out.println("Clicked!");
        SearchWatcherActivity.toggle();



    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);
        if (convertView != null) {


            ImageView imageView = (ImageView) convertView.findViewById(R.id.hamButton);

            System.out.println(imageView);
            imageView.setOnClickListener(this);
            SearchWatcherActivity.toggle();
        }

        return convertView;
    }
}
