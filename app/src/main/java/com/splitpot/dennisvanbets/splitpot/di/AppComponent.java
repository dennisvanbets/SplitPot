package com.splitpot.dennisvanbets.splitpot.di;

import android.app.Application;

import com.splitpot.dennisvanbets.splitpot.SplitPotApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by DennisVanBets on 30/08/2017.
 */

@Component(modules = {AndroidInjectionModule.class,AppModule.class,ActivityBuilder.class})
public interface AppComponent{

    @Component.Builder
    interface Builder{
        @BindsInstance Builder application(Application application);
        AppComponent build();
    }

    void inject(SplitPotApplication application);
}
