package com.splitpot.dennisvanbets.splitpot.ui.main;

import com.splitpot.dennisvanbets.splitpot.dao.di.DbModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by DennisVanBets on 30/08/2017.
 */
@Subcomponent(modules = {
        MainActivityModule.class,
        DbModule.class
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }
}

