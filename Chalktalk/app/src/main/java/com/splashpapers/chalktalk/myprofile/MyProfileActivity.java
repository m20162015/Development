package com.splashpapers.chalktalk.myprofile;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.mykids.MyKidsFragment;

/**
 * Created by manishsharma on 11/28/16.
 */
public class MyProfileActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.main_activity);

        FragmentManager fm=this.getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();


        MyKidsFragment myKidsFragment=new MyKidsFragment();
        ft.add(R.id.main_container,myKidsFragment);
        ft.commit();
        fm.executePendingTransactions();
    }
}
