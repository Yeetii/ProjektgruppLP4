package se.chalmers.projektgrupplp4.studentlivinggbg.view;

/**
 * @author John
 */

interface RecyclerViewHolderObservable {
    void add(RecyclerViewHolderObserver recyclerViewHolderObserver);
    void notifyObservers();

}
