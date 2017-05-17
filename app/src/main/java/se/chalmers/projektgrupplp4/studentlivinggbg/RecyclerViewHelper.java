package se.chalmers.projektgrupplp4.studentlivinggbg;

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

/**
 * Created by Jonathan on 09/05/2017.
 */

public class RecyclerViewHelper {

    private Activity activity;

    private AccommodationRecyclerViewAdapter recyclerViewAdapter;

    public RecyclerViewHelper (Activity activity, AccommodationRecyclerViewAdapter adapter) {
        this.activity = activity;
        this.recyclerViewAdapter = adapter;
    }

    private void onChildDrawController(Canvas c, RecyclerView.ViewHolder viewHolder, float dX, int actionState) {
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            AccommodationRecyclerViewHolder accommodation = (AccommodationRecyclerViewHolder) viewHolder;
            if((dX > 0 && !accommodation.isFavorite()) || accommodation.isFavorite()) {
                draw(c, accommodation.isFavorite(), viewHolder, dX);
            }
        }
    }


    public void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                if (direction != ItemTouchHelper.UP && direction != ItemTouchHelper.DOWN){
                    if (recyclerViewAdapter != null) {
                        int position = viewHolder.getAdapterPosition();
                        //Accommodation accommodation = recyclerViewAdapter.getAccommodations().get(position);
                        recyclerViewAdapter.setFavorite(position, direction != ItemTouchHelper.LEFT);
                        System.out.println(direction != ItemTouchHelper.LEFT);
                        //accommodation.setFavorite(direction != ItemTouchHelper.LEFT);

                        recyclerViewAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
                    }
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                onChildDrawController(c, viewHolder, dX, actionState);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        attachItemTouchHelperToRecycleView(simpleItemTouchCallback);
    }

    private void attachItemTouchHelperToRecycleView (ItemTouchHelper.SimpleCallback simpleItemTouchCallback) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView((RecyclerView) activity.findViewById(R.id.list));
    }

    private void draw(Canvas c, boolean favorite, RecyclerView.ViewHolder viewHolder, float dX) {
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
