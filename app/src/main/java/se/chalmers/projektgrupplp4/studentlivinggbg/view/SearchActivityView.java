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

    public void displayChangeFavorite(int actionState, float dX, Canvas c, RecyclerView.ViewHolder viewHolder) {
        Bitmap icon;
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

            AccommodationRecyclerViewHolder accommodation = (AccommodationRecyclerViewHolder) viewHolder;

            View itemView = viewHolder.itemView;
            float height = (float) itemView.getBottom() - (float) itemView.getTop();
            float width = height / 3;

            if(dX > 0 && !accommodation.isFavorite()) {
                //p.setColor(Color.parseColor("#388E3C"));
                p.setColor(Color.YELLOW);
                RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                c.drawRect(background,p);
                icon = BitmapFactory.decodeResource(activity.getResources(), R.drawable.favorite);
                RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                c.drawBitmap(icon,null,icon_dest,p);
            } else if (accommodation.isFavorite()) {
                p.setColor(Color.parseColor("#D32F2F"));
                RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                c.drawRect(background,p);
                icon = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_delete_white);
                RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                c.drawBitmap(icon,null,icon_dest,p);
            }
        }
    }
}
