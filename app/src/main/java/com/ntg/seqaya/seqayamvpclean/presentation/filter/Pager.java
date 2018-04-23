package com.ntg.seqaya.seqayamvpclean.presentation.filter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by GM7 on 2018-04-21.
 */

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    private int tabCount;

    Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                return new BottlesSizeFragment();
            case 1:
                return new BottlesPriceFragment();
            case 2:
                return new BottlesNumFragment();
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
