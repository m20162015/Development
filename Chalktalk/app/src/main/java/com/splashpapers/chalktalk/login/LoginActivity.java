package com.splashpapers.chalktalk.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.splashpapers.chalktalk.R;

/**
 * Created by manishsharma on 11/9/16.
 */
public class LoginActivity extends AppCompatActivity {

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        this.getSupportActionBar().hide();
        context = this;

        // Get extra parameters
        Bundle extras = getIntent().getExtras();
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(extras);
        ft.add(R.id.main_container, loginFragment);
        ft.commit();
        fm.executePendingTransactions();
    }


}
