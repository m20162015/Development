package com.splashpapers.chalktalk.myprofile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.splashpapers.chalktalk.R;

/**
 * Created by manishsharma on 11/30/16.
 */
public class MyProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_screen_layout, container, false);
        return view;
    }
}
