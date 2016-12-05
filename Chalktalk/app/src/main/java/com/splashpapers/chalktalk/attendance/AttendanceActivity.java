package com.splashpapers.chalktalk.attendance;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;

import com.splashpapers.chalktalk.R;

/**
 * Created by manishsharma on 11/28/16.
 */
public class AttendanceActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.main_activity);
    }
}
