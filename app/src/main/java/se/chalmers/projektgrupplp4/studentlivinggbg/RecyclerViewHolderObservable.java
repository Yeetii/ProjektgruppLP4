package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.widget.ImageView;

/**
 * Created by PG on 11/04/2017.
 */

public interface ImageViewObservable {
    void add(ImageViewObserver imageViewObserver);
    void notifyObservers();

}
