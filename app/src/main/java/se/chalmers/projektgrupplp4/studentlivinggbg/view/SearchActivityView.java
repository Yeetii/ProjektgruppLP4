package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import se.chalmers.projektgrupplp4.studentlivinggbg.AccommodationRecyclerViewHolder;
import se.chalmers.projektgrupplp4.studentlivinggbg.AdvancedSearchActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.SearchActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by PG on 28/04/2017.
 */

public class SearchActivityView {
    private Activity activity;
    private Paint p = new Paint();

    public SearchActivityView (Activity activity) {
        this.activity = activity;
        activity.setContentView(R.layout.activity_main_search);
    }

    public void initLayoutManager(SearchActivityModel model) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        model.getRecyclerView().setLayoutManager(linearLayoutManager);
    }

    public void openAdvancedSearch () {
        Intent intent = new Intent(activity, AdvancedSearchActivity.class);
        activity.startActivity(intent);
    }

}
