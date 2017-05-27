package se.chalmers.projektgrupplp4.studentlivinggbg.view;

/**
 * @author John
 * Used by: AccommodationRecyclerViewAdapter, AccommodationRecyclerViewHolder, RecyclerViewHolderObservable
 * Uses: AccommodationRecyclerViewHolder
 * Responsibility: interface for recyclerview holders
 */

interface RecyclerViewHolderObserver {
    void update(AccommodationRecyclerViewHolder viewHolder);
}
