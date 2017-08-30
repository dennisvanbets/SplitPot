package com.splitpot.dennisvanbets.splitpot.di;

import android.app.Application;
import android.content.Context;

import com.splitpot.dennisvanbets.splitpot.ui.main.MainActivityComponent;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;


/**
 * Created by DennisVanBets on 29/08/2017.
 */
@Module(subcomponents = {
        MainActivityComponent.class,
})
abstract class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }
}

