package com.splitpot.dennisvanbets.splitpot.di;

import android.app.Activity;
import android.app.Fragment;

import com.splitpot.dennisvanbets.splitpot.ui.main.MainActivity;
import com.splitpot.dennisvanbets.splitpot.ui.main.MainActivityComponent;
import com.splitpot.dennisvanbets.splitpot.ui.pot.PotListFragment;
import com.splitpot.dennisvanbets.splitpot.ui.pot.PotListFragmentComponent;
import com.splitpot.dennisvanbets.splitpot.ui.user.UserListFragment;
import com.splitpot.dennisvanbets.splitpot.ui.user.UserListFragmentComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Created by DennisVanBets on 30/08/2017.
 */

@Module
public abstract class ActivityBuilder {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivity(MainActivityComponent.Builder builder);
}
