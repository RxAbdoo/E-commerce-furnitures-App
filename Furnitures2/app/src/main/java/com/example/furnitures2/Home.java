package com.example.furnitures2;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment {
    Button b;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        v = inflater.inflate(R.layout.fragment_home, container, false);
        b = v.findViewById(R.id.button3);
        TabLayout tabLayout = v.findViewById(R.id.tabLayout);
        ViewPager pager2=  v.findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getParentFragmentManager());
        ArrayList<MyTab> myTabs = new ArrayList<>();
        myTabs.add(new MyTab("All",new All_f()));
        myTabs.add(new MyTab("Chairs",new Chairs_f()));
        myTabs.add(new MyTab("Sofa",new Sofa_f()));
        myTabs.add(new MyTab("Tables",new Tables_f()));
        myTabs.add(new MyTab("Lamps",new Lamps_f ()));
        adapter.setMyTabs(myTabs);
        pager2.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager2);
        return  v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final   NavController navController = Navigation.findNavController(view);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "checked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}