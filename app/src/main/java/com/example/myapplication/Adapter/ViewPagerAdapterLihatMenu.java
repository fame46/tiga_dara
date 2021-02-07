package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.Admin.LihatMakananFragment;
import com.example.myapplication.Admin.LihatMinumanFragment;
import com.example.myapplication.Admin.LihatPaketFragment;
import com.example.myapplication.Admin.UploadMakananFragment;
import com.example.myapplication.Admin.UploadMinumanFragment;
import com.example.myapplication.Admin.UploadPaketFragment;

public class ViewPagerAdapterLihatMenu extends FragmentStatePagerAdapter {

    int tabCount;

    //Constructor to the class
    public ViewPagerAdapterLihatMenu(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LihatMakananFragment tab1 = new LihatMakananFragment();
                return tab1;
            case 1:
                LihatMinumanFragment tab2 = new LihatMinumanFragment();
                return tab2;
            case 2:
                LihatPaketFragment tab3 = new LihatPaketFragment();
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
