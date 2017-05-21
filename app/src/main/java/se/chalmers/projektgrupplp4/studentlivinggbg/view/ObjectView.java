package se.chalmers.projektgrupplp4.studentlivinggbg.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ObjectView {

    private Activity activity;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will AccommodationHost the section contents.
     */
    private ViewPager mViewPager;


    public ObjectView (Activity activity) {
        this.activity = activity;
        this.activity.setContentView(R.layout.activity_object);
        ObjectActivity test = (ObjectActivity) activity;
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        //TODO better way to do this??
        mSectionsPagerAdapter = new SectionsPagerAdapter(test.getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) activity.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_NUMBER = "object_number";
        private static final String ARG_ADDRESS = "object_address";
        private static final String ARG_DESCRIPTION = "object_description";
        private static final String ARG_RENT = "object_rent";
        private static final String ARG_TYPE = "object_type";
        private static final String ARG_AREA = "object_area";
        private static final String ARG_HOST = "object_host";
        private static final String ARG_DATE = "object_date";
        private static final String ARG_FURNITURED = "object_furnitured";

        private Drawable image = null;


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(Accommodation a) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();

            args.putString(ARG_ADDRESS, a.getAddress());
            args.putString(ARG_DESCRIPTION, a.getDescription());
            args.putString(ARG_RENT, a.getPrice());
            args.putString(ARG_TYPE, a.getAccommodationHouseType());
            args.putString(ARG_AREA, a.getArea());
            args.putString(ARG_HOST, a.getAccommodationHost());
            args.putString(ARG_DATE, a.getLastApplyDate());
            args.putString(ARG_FURNITURED, a.getFurnitured());
            fragment.setImage(ImageModel.<Drawable>getInstance().getMainImage(a.getImagePath()));
            fragment.setArguments(args);
            return fragment;
        }

        public void setImage (Drawable image) {
            this.image = image;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_object, container, false);
            TextView description = (TextView) rootView.findViewById(R.id.description_lbl);
            TextView rent = (TextView) rootView.findViewById(R.id.rent);
            TextView type = (TextView) rootView.findViewById(R.id.type);
            TextView area = (TextView) rootView.findViewById(R.id.area);
            TextView host = (TextView) rootView.findViewById(R.id.host);
            TextView date = (TextView) rootView.findViewById(R.id.date_lbl);
            TextView furnitured = (TextView) rootView.findViewById(R.id.furnitured_lbl);

            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            imageView.setImageDrawable(image);

            description.setText(getArguments().getString(ARG_DESCRIPTION));
            rent.setText(getArguments().getString(ARG_RENT) + " kr");
            type.setText(getArguments().getString(ARG_TYPE));
            area.setText(getArguments().getString(ARG_AREA) + " m²");
            host.setText(getArguments().getString(ARG_HOST));
            date.setText(getArguments().getString(ARG_DATE));
            furnitured.setText(getArguments().getString(ARG_FURNITURED));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            return PlaceholderFragment.newInstance(ObjectActivityModel.getAccommodations().get(position));
        }

        @Override
        public int getCount() {
            return ObjectActivityModel.getAccommodations().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position > mViewPager.getChildCount())
                return null;
            return ObjectActivityModel.getAccommodations().get(position).getAddress();
        }
    }
}
