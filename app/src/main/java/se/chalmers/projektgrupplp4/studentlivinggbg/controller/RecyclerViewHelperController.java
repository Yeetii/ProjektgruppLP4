package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import se.chalmers.projektgrupplp4.studentlivinggbg.view.AccommodationRecyclerViewAdapter;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AccommodationRecyclerViewHolder;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.RecyclerViewHelperView;

/**
 * Created by Jonathan on 22/05/2017.
 */

public class RecyclerViewHelperController {

    private final AccommodationRecyclerViewAdapter recyclerViewAdapter;

    private final RecyclerViewHelperView recyclerViewHelperView;

    public RecyclerViewHelperController (Activity activity, AccommodationRecyclerViewAdapter adapter) {
        this.recyclerViewAdapter = adapter;
        this.recyclerViewHelperView = new RecyclerViewHelperView(activity);
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
        recyclerViewHelperView.attachItemTouchHelperToRecycleView(simpleItemTouchCallback);
    }

    private void onChildDrawController(Canvas c, RecyclerView.ViewHolder viewHolder, float dX, int actionState) {
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            AccommodationRecyclerViewHolder accommodation = (AccommodationRecyclerViewHolder) viewHolder;
            if((dX > 0 && !accommodation.isFavorite()) || accommodation.isFavorite()) {
                recyclerViewHelperView.draw(c, accommodation.isFavorite(), viewHolder, dX);
            }
        }
    }
}
