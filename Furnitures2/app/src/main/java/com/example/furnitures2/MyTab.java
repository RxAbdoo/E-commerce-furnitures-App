package com.example.furnitures2;

import androidx.fragment.app.Fragment;

public class MyTab {
    String Mytab_Name;
    Fragment fragment;

    public MyTab(String mytab_Name) {
        Mytab_Name = mytab_Name;
    }

    public String getMytab_Name() {
        return Mytab_Name;
    }

    public void setMytab_Name(String mytab_Name) {
        Mytab_Name = mytab_Name;
    }

    public MyTab(String mytab_Name, Fragment fragment) {
        Mytab_Name = mytab_Name;
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
