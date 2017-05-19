package se.chalmers.projektgrupplp4.studentlivinggbg.view;

/**
 * Created by PG on 11/04/2017.
 */

public interface RecyclerViewHolderObservable {
    void add(RecyclerViewHolderObserver recyclerViewHolderObserver);
    void notifyObservers();

}
