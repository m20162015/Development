package com.splashpapers.chalktalk.homework;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.notices.NoticesFragment;
import com.splashpapers.chalktalk.notification.NotificationListFragment;
import com.splashpapers.chalktalk.notification.NotificationSetAlertFragment;

/**
 * Created by manishsharma on 11/23/16.
 */
public class HomeWorkActivity  extends ActionBarActivity {
    ActionBar mActionBar;
    public static Context context;
    private NotificationListFragment notificationViewList;
    private NotificationSetAlertFragment notificationSetAlertList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mActionBar=getSupportActionBar();
        mActionBar.setTitle("HOMEWORK");

        context=this;

        FragmentManager fm=this.getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        Bundle extras=getIntent().getExtras();

        HomeWorkFragment homeWorkFragment=new HomeWorkFragment();
        homeWorkFragment.setArguments(extras);
        ft.add(R.id.main_container,homeWorkFragment);
        ft.commit();
        fm.executePendingTransactions();

    }
}
