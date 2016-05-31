package com.example.user.enssingasari.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.user.enssingasari.R;

public class MainMenuActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    public SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public ViewPager mViewPager;

    /**
     * A placeholder fragment containing a simple view.
     */
    Button left_button;
    Button right_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //activity menu
        setContentView(R.layout.activity_main_menu);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        left_button = (Button) findViewById(R.id.left_butoon);
        right_button = (Button) findViewById(R.id.right_button);
        setVisibilityButton();

        left_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1);
                setVisibilityButton();
            }

        });

        right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
                setVisibilityButton();
            }
        });


    }

    private void setVisibilityButton(){
        if(mViewPager.getCurrentItem() == 0){
            left_button.setVisibility(View.INVISIBLE);
            right_button.setVisibility(View.VISIBLE);
        }else if (mViewPager.getCurrentItem() == 2){
            right_button.setVisibility(View.INVISIBLE);
            left_button.setVisibility(View.VISIBLE);
        }else{
            right_button.setVisibility(View.VISIBLE);
            left_button.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public static class PlaceholderFragment extends Fragment implements View.OnClickListener{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 final Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

            final ImageView imageView = (ImageView) rootView.findViewById(R.id.image_label);
            imageView.setImageResource(getImageMenu(getArguments().getInt(ARG_SECTION_NUMBER)));
            imageView.setOnClickListener(this);

            return rootView;
        }

        private int getImageMenu(int i){
            switch (i){
                case 1:
                    return R.drawable.mainmenu_tokoh;
                case 2:
                    return R.drawable.mainmenu_sejarah;
                case 3:
                    return R.drawable.mainmenu_artefak;
            }
            return 0;
        }

        @Override
        public void onClick(View v) {
            //for new activity after menu
            Intent i;
            Activity act = getActivity();
            switch ((getArguments().getInt(ARG_SECTION_NUMBER))){
                case 1:
                    //Tokoh menu
                    i = new Intent(act,TokohMenuActivity.class);
                    startActivity(i);
                    break;
                case 2:
                    //Timeline menu
                    i = new Intent(act,Timeline.class);
                    startActivity(i);
                    break;
                case 3:
                    //Peta Menu
                    i = new Intent(act,MenuArtefak.class);
                    startActivity(i);
                    break;
            }
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
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
