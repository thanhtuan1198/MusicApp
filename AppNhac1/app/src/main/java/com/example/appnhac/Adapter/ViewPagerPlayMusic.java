package com.example.appnhac.Adapter;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerPlayMusic extends FragmentPagerAdapter {
    public final ArrayList<Fragment> arrayListFragment = new ArrayList<>();
    public ViewPagerPlayMusic(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return arrayListFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayListFragment.size();
    }
    public void addfragment(Fragment fragment){
        arrayListFragment.add(fragment);
    }
}
