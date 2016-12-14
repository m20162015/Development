package com.splashpapers.chalktalk.notification;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.app.AppController;

/**
 * Created by manishsharma on 12/10/16.
 */

public class NotificationPageAdapter extends FragmentStatePagerAdapter {

    public NotificationPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new NotificationListFragment();
            case 1:
                return new NotificationSetAlertFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return AppController.getAppContext().getString(R.string.notif_tab1);
            case 1:
                return AppController.getAppContext().getString(R.string.notif_tab2);
            default: return null;
        }
    }
}
