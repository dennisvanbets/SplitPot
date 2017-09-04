package com.splitpot.dennisvanbets.splitpot.ui.main;

import android.annotation.SuppressLint;

import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.splitpot.dennisvanbets.splitpot.R;
import com.splitpot.dennisvanbets.splitpot.dao.SplitPotDao;
import com.splitpot.dennisvanbets.splitpot.dao.SplitPotDaoSQLite;
import com.splitpot.dennisvanbets.splitpot.ui.pot.PotListFragment;
import com.splitpot.dennisvanbets.splitpot.ui.user.UserListFragment;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public class MainActivity extends AppCompatActivity {
    private SplitPotDaoSQLite db;
    private MainPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = SplitPotDaoSQLite.getInstance(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_coins).setText("Pots"));
        //tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_people).setText("Users"));
        tabLayout.addTab(tabLayout.newTab().setText("Pots"));
        tabLayout.addTab(tabLayout.newTab().setText("Users"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.mainviewpager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}

