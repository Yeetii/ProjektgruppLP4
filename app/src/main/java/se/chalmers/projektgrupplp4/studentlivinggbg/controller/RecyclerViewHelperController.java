package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.AccommodationRecyclerViewHolder;
import se.chalmers.projektgrupplp4.studentlivinggbg.view.RecyclerViewHelperView;

/**
 * @author Jonathan Gildevall
 * Revised by: Erik Magnusson
 * Uses: Accommodation, AccommodationRecyclerViewAdapter, AccommodationRecyclerViewHolder, RecyclerViewHelperView
 * Used by: FavoritesActivity, SearchActivityController
 * Responsibility: Controller for the accommodations display in the list views.
 */

public class RecyclerViewHelperController {

    private final AccommodationRecyclerViewAdapter recyclerViewAdapter;

    private final RecyclerViewHelperView recyclerViewHelperView;

    public RecyclerViewHelperController (Activity activity, AccommodationRecyclerViewAdapter adapter) {
        this.recyclerViewAdapter = adapter;
        this.recyclerViewHelperView = new RecyclerViewHelperView(activity);
        initAdapter(activity, adapter);
        initSwipe();
    }

    private void initAdapter(Activity activity, AccommodationRecyclerViewAdapter adapter) {
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.list);
        recyclerView.setAdapter(adapter);
    }

    private void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                if (direction != ItemTouchHelper.UP && direction != ItemTouchHelper.DOWN && recyclerViewAdapter != null){
                    int position = viewHolder.getAdapterPosition();
                    Accommodation accommodation = recyclerViewAdapter.getAccommodations().get(position);
                    accommodation.setFavorite(direction != ItemTouchHelper.LEFT);

                    recyclerViewAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
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
