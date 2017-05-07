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

    public void draw(Canvas c, boolean favorite, RecyclerView.ViewHolder viewHolder, float dX) {
        Paint paint = new Paint();
        View itemView = viewHolder.itemView;
        float height = (float) itemView.getBottom() - (float) itemView.getTop();
        float width = height / 3;
        int color = Color.parseColor(favorite ? "#D32F2F" : "#388E3C");
        int drawableIcon = favorite ? R.drawable.ic_delete_white : R.drawable.ic_edit_white;
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
