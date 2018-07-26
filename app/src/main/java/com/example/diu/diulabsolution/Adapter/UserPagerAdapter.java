package com.example.diu.diulabsolution.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.diu.diulabsolution.Fragment.HomeFragment;
import com.example.diu.diulabsolution.Fragment.AuthorityNotificationFragment;
import com.example.diu.diulabsolution.Fragment.ProfileFragment;
import com.example.diu.diulabsolution.Fragment.UserNotificationFragment;
import com.example.diu.diulabsolution.R;

public class UserPagerAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link AuthorityPagerAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public UserPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeFragment();
        }
        else if (position == 1) {
            return new UserNotificationFragment();
        } else {
            return new ProfileFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.home_title);
        }
        else if (position == 1) {
            return mContext.getString(R.string.notification_title);
        }  else {
            return mContext.getString(R.string.profile_title);
        }
    }
}
