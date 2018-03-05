package com.letbemagi.magi.domma.Tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.letbemagi.magi.domma.Fragment.BudgetFragment;
import com.letbemagi.magi.domma.Fragment.CategoriesFragment;
import com.letbemagi.magi.domma.Fragment.DompetFragment;
import com.letbemagi.magi.domma.Fragment.HomeFragment;
import com.letbemagi.magi.domma.Fragment.TransactionFragment;
import com.letbemagi.magi.domma.Fragment.WishListFragment;

/**
 * Created by fakrypermana on 08/11/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTab;

    public PagerAdapter(FragmentManager fm, int numOfTab) {
        super(fm);
        this.numOfTab = numOfTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                HomeFragment frag1 = new HomeFragment();
                return frag1;
            case 1:
                DompetFragment frag2 = new DompetFragment();
                return frag2;
            case 2:
                CategoriesFragment frag3 = new CategoriesFragment();
                return frag3;
            case 3:
                TransactionFragment frag4 = new TransactionFragment();
                return frag4;
            case 4:
                BudgetFragment frag5 = new BudgetFragment();
                return frag5;
            case 5:
                WishListFragment frag6 = new WishListFragment();
                return frag6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTab;
    }
}
