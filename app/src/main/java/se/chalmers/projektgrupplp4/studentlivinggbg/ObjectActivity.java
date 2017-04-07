package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;

import se.chalmers.projektgrupplp4.studentlivinggbg.Model.Accommodation;

public class ObjectActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private ArrayList<Accommodation> accommodations = new ArrayList<>();
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will AccommodationHost the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

        System.out.println(getIntent().getStringExtra("ARG_POSITION"));
        accommodations.add(new Accommodation("Gibraltargatan"));
        accommodations.add(new Accommodation("Chalmers"));
        accommodations.add(new Accommodation("Skåne"));
        accommodations.add(new Accommodation("Ingenstans"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(getIntent().getIntExtra("ARG_POSITION", 0));


        //Todo Implement favorites
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Lägger till som favorit", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_object, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //Used for updating title
    @Override
    public void onPageSelected(int position) {
        setTitle(accommodations.get(position).getAddress());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_object, container, false);
            TextView description = (TextView) rootView.findViewById(R.id.description);
            TextView rent = (TextView) rootView.findViewById(R.id.rent);
            TextView type = (TextView) rootView.findViewById(R.id.type);
            TextView area = (TextView) rootView.findViewById(R.id.area);
            TextView host = (TextView) rootView.findViewById(R.id.host);

            description.setText(getArguments().getString(ARG_DESCRIPTION));
            rent.setText(getArguments().getString(ARG_RENT) + "kr");
            type.setText(getArguments().getString(ARG_TYPE));
            area.setText(getArguments().getString(ARG_AREA) + "kvm");
            host.setText(getArguments().getString(ARG_HOST));
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

            return PlaceholderFragment.newInstance(accommodations.get(position));
        }

        @Override
        public int getCount() {
            return accommodations.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position > mViewPager.getChildCount())
                return null;
            return accommodations.get(position).getAddress();
        }
    }
}
