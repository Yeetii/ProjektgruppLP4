package se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher;

import android.view.View;

import se.chalmers.projektgrupplp4.studentlivinggbg.view.searchwatcher.ModalView;

/**
 * Created by Erik on 2017-05-24.
 */

public interface ViewCreationObserver {
    void viewCreated(View view, ModalView modalFragment);
}
