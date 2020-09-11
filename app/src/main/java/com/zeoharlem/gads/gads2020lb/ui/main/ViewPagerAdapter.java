package com.zeoharlem.gads.gads2020lb.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zeoharlem.gads.gads2020lb.Models.LearnersBoard;
import com.zeoharlem.gads.gads2020lb.R;
import com.zeoharlem.gads.gads2020lb.Services.LearnersService;
import com.zeoharlem.gads.gads2020lb.Services.MessageService;
import com.zeoharlem.gads.gads2020lb.Services.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};

    private static final List<Fragment> fragmentList    = new ArrayList<>();
    private static final List<String> titleString       = new ArrayList<>();
    private final Context mContext;

    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
        //return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //return mContext.getResources().getString(TAB_TITLES[position]);
        return titleString.get(position);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return titleString.size();
    }

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        titleString.add(title);
    }


}