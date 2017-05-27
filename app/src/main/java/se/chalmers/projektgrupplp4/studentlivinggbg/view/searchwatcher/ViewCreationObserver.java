package se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher;

import android.view.View;

import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.ModalView;

/**
 * Created by Erik on 2017-05-24.
 * @author Erik
 * Used by: ModalView, SearchWatcherController, SearchWatcherItemController
 * Uses: ModalView
 * Responsibility: Interface for view observers
 */

public interface ViewCreationObserver {
    void viewCreated(View view, ModalView modalFragment);
}
