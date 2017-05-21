package se.chalmers.projektgrupplp4.studentlivinggbg.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.activity.ObjectActivity;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.ImageModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.ObjectActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;

/**
 * Created by Jonathan on 21/05/2017.
 */

public class ObjectController {

    private Activity activity;

    private Button applyButton;
    /**
     * The {@link ViewPager} that will AccommodationHost the section contents.
     */
    private ViewPager mViewPager;

    public ObjectController (Activity activity) {
        this.activity = activity;
        initListeners();
    }

    private void initListeners () {
        applyButton = (Button) activity.findViewById(R.id.button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com");
                Accommodation accommodation = Accommodation.getAccommodations().get(activity.getIntent().getIntExtra("ARG_POSITION",0));
                if (accommodation.getAccommodationHost().equals("SGS Studentbostäder")) {
                    uri = Uri.parse(ObjectActivityModel.getsGSUrl() + accommodation.getObjectNumber());
                } else if (accommodation.getAccommodationHost().equals("Chalmers Studentbostäder")) {
                    uri = Uri.parse(ObjectActivityModel.getChalmersUrl() + accommodation.getObjectNumber());
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }
        });

        //Todo Implement favorites
        FloatingActionButton fab = (FloatingActionButton) activity.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Lägger till som favorit", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ImageButton close = (ImageButton) activity.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });

        mViewPager = (ViewPager) activity.findViewById(R.id.container);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                activity.setTitle(ObjectActivityModel.getAccommodations().get(position).getAddress());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(activity.getIntent().getIntExtra("ARG_POSITION", 0));

    }


}
