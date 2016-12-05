package com.splashpapers.chalktalk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.home.HomeActivity;
import com.splashpapers.chalktalk.login.LoginActivity;

/**
 * Created by manishsharma on 11/18/16.
 */
public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        final boolean isLogin = sharedPreferences.getBoolean(Constants.LOGIN, false);
        final boolean isFirstTime = sharedPreferences.getBoolean(Constants.FIRST_TIME, true);

        Thread logoTimer = new Thread() {
            public void run() {
                try {
                    int logoTimer = 0;
                    while (logoTimer < 1000) {
                        sleep(100);
                        logoTimer = logoTimer + 100;
                    }

                    if (isLogin) {
                        Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                        startActivity(intent);
                        finish();
                    } else {
                        // Check if the user enter for first time to the app
//                        if (isFirstTime) {
//                            startActivity(new Intent(SplashScreen.this, WelcomeActivity.class));
//                        } else {
                            startActivity(new Intent(SplashScreen.this, LoginActivity.class));
//                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };
        logoTimer.start();
    }

}
