package com.splashpapers.chalktalk.timetable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.app.AppController;

/**
 * Created by manishsharma on 11/24/16.
 */
public class TimeTablePageAdapter extends FragmentStatePagerAdapter {

    public TimeTablePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new TimeTableFragmentMonday();
            case 1:
                return new TimeTableFragmentTuesday();
            case 2:
                return new TimeTableFragmentWednesday();
            case 3:
                return new TimeTableFragmentThrusday();
            case 4:
                return new TimeTableFragmentFriday();
            case 5:
                return new TimeTableFragmentSaturday();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return AppController.getAppContext().getString(R.string.Monday);
            case 1:
                return AppController.getAppContext().getString(R.string.Tuesday);
            case 2:
                return AppController.getAppContext().getString(R.string.wednesday);
            case 3:
                return AppController.getAppContext().getString(R.string.thursday);
            case 4:
                return AppController.getAppContext().getString(R.string.friday);
            case 5:
                return AppController.getAppContext().getString(R.string.saturday);
            default: return null;
        }
    }
}
