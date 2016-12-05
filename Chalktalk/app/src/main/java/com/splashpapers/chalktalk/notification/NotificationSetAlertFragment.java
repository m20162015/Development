package com.splashpapers.chalktalk.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.splashpapers.chalktalk.R;

/**
 * Created by my pc on 20-Nov-16.
 */

public class NotificationSetAlertFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notification_list_view_layout, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);

    }
    @Override
    public void onCreate( Bundle savedInstanceState) {
       // String screenName = SCREEN_NAME;

        super.onCreate(savedInstanceState);
    }
}
