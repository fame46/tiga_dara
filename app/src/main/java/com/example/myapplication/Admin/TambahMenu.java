package com.example.myapplication.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.ViewPagerAdapter;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

public class TambahMenu extends Fragment implements TabLayout.BaseOnTabSelectedListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tambah_menu, container, false);

        tabLayout = (TabLayout) v.findViewById(R.id.tablayout_id);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_restaurant_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_free_breakfast_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_room_service_24));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) v.findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(this);

        return v;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}