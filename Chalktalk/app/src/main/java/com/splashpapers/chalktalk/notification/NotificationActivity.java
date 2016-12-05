package com.splashpapers.chalktalk.notification;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.splashpapers.chalktalk.R;



/**
 * Created by my pc on 16-Nov-16.
 */

public class NotificationActivity  extends ActionBarActivity  implements ActionBar.TabListener{
    ActionBar mActionBar;
    public static Context context;
    private NotificationListFragment notificationViewList;
    private NotificationSetAlertFragment notificationSetAlertList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  TabHost tabHost = (TabHost)findViewById(R.id.notif_tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("first tab");
        TabHost.TabSpec tab2=tabHost.newTabSpec("second tab");

        tab1.setIndicator("VIEW NOTIFICATION");
        tab1.setContent(new Intent(this,Tab1Activity.class));

        tab2.setIndicator("SET ALERT");
        tab2.setContent(new Intent(this,Tab2Activity.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2); */

        notificationViewList= new NotificationListFragment();
        notificationSetAlertList=new NotificationSetAlertFragment();


        mActionBar = getSupportActionBar();
        mActionBar.setTitle("NOTIFICATION");
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1 = mActionBar.newTab().setText(getString(R.string.notif_tab1))
                .setTag(NotificationConstants.ACTUAL).setTabListener(this);
        ActionBar.Tab tab2 = mActionBar.newTab().setText(getString(R.string.notif_tab2))
                .setTag(NotificationConstants.PAST).setTabListener(this);

        mActionBar.addTab(tab1);
        mActionBar.addTab(tab2);

        context = this;

        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Bundle extras = getIntent().getExtras();

        NotificationListFragment taskList = new NotificationListFragment();
        taskList.setArguments(extras);

        ft.add(R.id.main_container,taskList);
        ft.commit();
        fm.executePendingTransactions();

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        if (notificationViewList != null) {
            FragmentManager fm = this.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (tab.getTag().equals(NotificationConstants.ACTUAL)) {
                ft.replace(R.id.main_container, notificationViewList);
                ft.commit();
            } else {
                ft.replace(R.id.main_container, notificationSetAlertList);
                ft.commit();
            }
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
