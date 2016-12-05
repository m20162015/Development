package com.splashpapers.chalktalk.timetable;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.splashpapers.chalktalk.R;

/**
 * Created by manishsharma on 11/24/16.
 */
public class TimeTableActivity  extends ActionBarActivity{
    ActionBar mActionBar;
    public static Context context;

    public TimeTablePageAdapter mTimeTablePageAdapter;
    private ViewPager mViewPager;

    private TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_activity);

        mActionBar=getSupportActionBar();
        mActionBar.setTitle("TIMETABLE");

        context=this;

        mTimeTablePageAdapter = new TimeTablePageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mTimeTablePageAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
//        mTabLayout.setupWithViewPager(mViewPager);
//        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
//            @Override
//            public void onPageSelected(int position) {
//                // When swiping between pages, select the
//                // corresponding tab.
//                mActionBar.setSelectedNavigationItem(position);
//            }
//
//        });
        setupTabLayout(mTabLayout);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                Log.d("Call Tab","Postion "+tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }

    private void setupTabLayout(TabLayout tabLayout) {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(mViewPager);
    }

//    @Override
//    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//        mViewPager.setCurrentItem(tab.getPosition());
//    }
//
//    @Override
//    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//    }
//
//    @Override
//    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//    }


}

