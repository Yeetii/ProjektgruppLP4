package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;

/**
 * Created by Jonathan on 09/05/2017.
 */

public class RecyclerViewHelperView {

    private final Activity activity;

    public RecyclerViewHelperView(Activity activity) {
        this.activity = activity;
    }

    public void attachItemTouchHelperToRecycleView (ItemTouchHelper.SimpleCallback simpleItemTouchCallback) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView((RecyclerView) activity.findViewById(R.id.list));
    }

    public void draw(Canvas c, boolean favorite, RecyclerView.ViewHolder viewHolder, float dX) {
        Paint paint = new Paint();
        View itemView = viewHolder.itemView;
        float height = (float) itemView.getBottom() - (float) itemView.getTop();
        float width = height / 3;
        int color = Color.parseColor(favorite ? "#D32F2F" : "#FFFF00");
        int drawableIcon = favorite ? R.drawable.ic_delete_white : R.drawable.favorite;
        RectF background;
        RectF iconDest;
        Bitmap icon;
        paint.setColor(color);

        if (favorite) {
            iconDest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
            background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
        } else {
            background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
            iconDest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
        }
        c.drawRect(background,paint);
        icon = BitmapFactory.decodeResource(activity.getResources(), drawableIcon);
        c.drawBitmap(icon,null,iconDest, paint);
    }
}
