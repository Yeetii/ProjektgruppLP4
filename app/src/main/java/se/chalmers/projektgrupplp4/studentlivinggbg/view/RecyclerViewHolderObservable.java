package se.chalmers.projektgrupplp4.studentlivinggbg.view;

/**
 * @author John
 * Used by: RecyclerViewHolderObservable
 * Uses: RecyclerViewHolderObserver
 * Responsibility: Interface for recyclerview holder observers
 */

interface RecyclerViewHolderObservable {
    void add(RecyclerViewHolderObserver recyclerViewHolderObserver);
    void notifyObservers();

}
