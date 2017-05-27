package se.chalmers.projektgrupplp4.studentlivinggbg.service;

/**
 * @author Peter Gärdenäs
 * Used by: AdvancedSearchActivityController, DatabaseUpdater, ModalController, Multispinner,
 * MultispinnerController, NameDialogController, RequestAccommodations, SearchWatcerAdapter,
 * SearchWatcherController.
 * Uses: (None).
 * Responsibility: An observer.
 */

public interface Observer {
    void update(String updateString);
}
