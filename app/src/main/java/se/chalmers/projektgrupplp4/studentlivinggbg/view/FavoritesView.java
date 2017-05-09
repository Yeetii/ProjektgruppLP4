package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by PG on 07/05/2017.
 */

public class FavoritesView {
    private RecyclerView recyclerView;
    private Activity activity;

    public FavoritesView(Activity activity) {
        this.activity = activity;
        activity.setContentView(R.layout.activity_favorites);
        initLayoutManger();
    }

    private void initLayoutManger() {
        recyclerView = (RecyclerView) activity.findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
