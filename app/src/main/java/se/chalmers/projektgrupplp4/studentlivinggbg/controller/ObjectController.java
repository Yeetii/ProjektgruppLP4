package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.ObjectActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;

/**
 * @author Jonathan Gildevall
 * Revised by: Erik Magnusson
 * Used by: ObjectActivity
 * Uses: ObjectActivityModel, Accommodation
 * Responsibility: Controller for the object activity.
 */

public class ObjectController {

    private Activity activity;
    private Accommodation current;
    private FloatingActionButton fab;

    public ObjectController (Activity activity) {
        this.activity = activity;
        initListeners();
    }

    private void initListeners () {
        Button applyButton = (Button) activity.findViewById(R.id.button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com");
                if (current.getAccommodationHost().equals("SGS Studentbostäder")) {
                    uri = Uri.parse(ObjectActivityModel.getsGSUrl() + current.getObjectNumber());
                } else if (current.getAccommodationHost().equals("Chalmers Studentbostäder")) {
                    uri = Uri.parse(ObjectActivityModel.getChalmersUrl() + current.getObjectNumber());
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }
        });

        fab = (FloatingActionButton) activity.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Lägger till som favorit", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                boolean newFavouriteStatus = toggleFabImage();
                current.setFavorite(newFavouriteStatus);
            }
        });

        ImageButton close = (ImageButton) activity.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });

        /*
      The {@link ViewPager} that will Host the section contents.
     */
        ViewPager mViewPager = (ViewPager) activity.findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current = ObjectActivityModel.getAccommodations().get(position);
                activity.setTitle(current.getAddress());
                setFabImage(current.getFavorite());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        current = ObjectActivityModel.getAccommodations().get(ObjectActivityModel.getStartPosition());
        mViewPager.setCurrentItem(activity.getIntent().getIntExtra("ARG_POSITION", ObjectActivityModel.getStartPosition()));

    }

    private boolean toggleFabImage() {
        boolean favourite = !current.getFavorite();
        setFabImage(favourite);
        return favourite;
    }
    private void setFabImage(boolean isFavourite){
        int drawable = isFavourite ? R.drawable.favorite_on : R.drawable.favorite_off;
        fab.setImageResource(drawable);
    }


}
