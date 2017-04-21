package se.chalmers.projektgrupplp4.studentlivinggbg;

/**
 * Created by PG on 11/04/2017.
 */

public interface Observable {
    void add(Observer observer);
    boolean remove(Observer observer);
    void notifyObservers();

}
