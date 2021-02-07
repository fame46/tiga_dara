package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.Admin.LihatMakananFragment;
import com.example.myapplication.Admin.LihatMinumanFragment;
import com.example.myapplication.Admin.LihatPaketFragment;
import com.example.myapplication.Pelayan.MakananFragment;
import com.example.myapplication.Pelayan.MinumanFragment;
import com.example.myapplication.Pelayan.PaketFragment;

public class ViewPagerAdapterPelayan extends FragmentStatePagerAdapter {

    int tabCount;

    //Constructor to the class
    public ViewPagerAdapterPelayan(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MakananFragment tab1 = new MakananFragment();
                return tab1;
            case 1:
                MinumanFragment tab2 = new MinumanFragment();
                return tab2;
            case 2:
                PaketFragment tab3 = new PaketFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
