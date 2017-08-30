package com.splitpot.dennisvanbets.splitpot.di;

import android.app.Activity;

import com.splitpot.dennisvanbets.splitpot.ui.main.MainActivity;
import com.splitpot.dennisvanbets.splitpot.ui.main.MainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
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
