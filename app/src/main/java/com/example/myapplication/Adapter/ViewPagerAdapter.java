package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.Admin.UploadMakananFragment;
import com.example.myapplication.Admin.UploadMinumanFragment;
import com.example.myapplication.Admin.UploadPaketFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    //Constructor to the class
    public ViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                UploadMakananFragment tab1 = new UploadMakananFragment();
                return tab1;
            case 1:
                UploadMinumanFragment tab2 = new UploadMinumanFragment();
                return tab2;
            case 2:
                UploadPaketFragment tab3 = new UploadPaketFragment();
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
