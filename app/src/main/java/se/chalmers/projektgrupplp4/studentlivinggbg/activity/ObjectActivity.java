package se.chalmers.projektgrupplp4.studentlivinggbg.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.StrictMode;
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

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import se.chalmers.projektgrupplp4.studentlivinggbg.R;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.accommodation.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.ObjectActivityModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.model.imagemodel.ImageModel;

public class ObjectActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

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
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

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

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com");
                //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                //startActivity(intent);
                //Intent intent = new Intent(ObjectActivity.this, ApplyActivity.class);
                Accommodation accommodation = Accommodation.getAccommodations().get(getIntent().getIntExtra("ARG_POSITION",0));
                if (accommodation.getAccommodationHost().equals("SGS Studentbostäder")) {
                    //String URL = "https://marknad.sgsstudentbostader.se/pgLogin.aspx?rurl=pgObjectInformation.aspx%3Fcompany%3D1%26obj%" + accommodation.getObjectNumber();
                    //intent.putExtra("URL", URL);
                    uri = Uri.parse("https://marknad.sgsstudentbostader.se/pgLogin.aspx?rurl=pgObjectInformation.aspx%3Fcompany%3D1%26obj%" + accommodation.getObjectNumber());

                } else if (accommodation.getAccommodationHost().equals("Chalmers Studentbostäder")) {
                    //TODO add procedure for CSB
                    //intent.putExtra("URL", "https://www.chalmersstudentbostader.se/");
                    uri = Uri.parse("https://www.chalmersstudentbostader.se/login/?returnUrl=https%3A%2F%2Fwww.chalmersstudentbostader.se%2Fsok-ledigt%2Fdetalj%2F%3Frefid%" + accommodation.getObjectNumber());
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });



        //Todo Implement favorites
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Lägger till som favorit", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ImageButton close = (ImageButton) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
        setTitle(ObjectActivityModel.getAccommodations().get(position).getAddress());
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
