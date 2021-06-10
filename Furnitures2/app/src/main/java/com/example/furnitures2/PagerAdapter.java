package com.example.furnitures2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<MyTab> myTabs = new ArrayList<>();

    public void setMyTabs(ArrayList<MyTab> myTabs) {
        this.myTabs = myTabs;
    }

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return myTabs.get(position).getFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return myTabs.get(position).getMytab_Name();
    }

    @Override
    public int getCount() {
        return myTabs.size();
    }
}
