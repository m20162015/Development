package com.splashpapers.chalktalk.aboutus;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.splashpapers.chalktalk.R;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Get extra parameters
        Bundle extras = getIntent().getExtras();
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AboutUsFragment aboutUsFragment = new AboutUsFragment();
        aboutUsFragment.setArguments(extras);
        ft.add(R.id.main_container, aboutUsFragment);
        ft.commit();
        fm.executePendingTransactions();
    }
}
