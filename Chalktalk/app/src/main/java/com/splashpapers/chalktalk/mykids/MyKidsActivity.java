package com.splashpapers.chalktalk.mykids;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.splashpapers.chalktalk.R;

/**
 * Created by manishsharma on 11/28/16.
 */
public class MyKidsActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        FragmentManager fm=this.getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        MyKidsFragment myKidsFragment=new MyKidsFragment();
        ft.add(R.id.main_container,myKidsFragment);
        ft.commit();
        fm.executePendingTransactions();
    }
}
