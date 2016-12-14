package com.splashpapers.chalktalk.notification;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.splashpapers.chalktalk.R;


/**
 * Created by my pc on 16-Nov-16.
 */

public class NotificationActivity  extends ActionBarActivity{
    ActionBar mActionBar;
    public static Context context;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    public NotificationPageAdapter mNotificationPageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);

      /*  TabHost tabHost = (TabHost)findViewById(R.id.notif_tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("first tab");
        TabHost.TabSpec tab2=tabHost.newTabSpec("second tab");

        tab1.setIndicator("VIEW NOTIFICATION");
        tab1.setContent(new Intent(this,Tab1Activity.class));

        tab2.setIndicator("SET ALERT");
        tab2.setContent(new Intent(this,Tab2Activity.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2); */


        mActionBar = getSupportActionBar();
//        mActionBar.setTitle("NOTIFICATION");

        context = this;

        mNotificationPageAdapter = new NotificationPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mNotificationPageAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        setupTabLayout(mTabLayout);

    }

    private void setupTabLayout(TabLayout tabLayout) {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(mViewPager);
    }
}
