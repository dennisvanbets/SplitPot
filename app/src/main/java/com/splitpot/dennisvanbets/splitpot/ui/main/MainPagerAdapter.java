package com.splitpot.dennisvanbets.splitpot.ui.main;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;

import com.splitpot.dennisvanbets.splitpot.ui.pot.PotListFragment;
import com.splitpot.dennisvanbets.splitpot.ui.user.UserListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    public MainPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Log.v("switch", "pos " + position);
        switch (position) {
            case 0:
                return new PotListFragment();
            case 1:
                return new UserListFragment();
            default:
                return new PotListFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        SpannableStringBuilder sb;
        switch (position) {
            case 0:
                sb = new SpannableStringBuilder("Pots");
                break;
            case 1:
                sb = new SpannableStringBuilder("Users");
                break;
            default:
                sb = new SpannableStringBuilder("Pots");
                break;
        }
        return sb;
    }
}
