package com.splitpot.dennisvanbets.splitpot.dao.di;

import com.splitpot.dennisvanbets.splitpot.dao.SplitPotDao;
import com.splitpot.dennisvanbets.splitpot.dao.SplitPotDaoSQLite;
import com.splitpot.dennisvanbets.splitpot.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DennisVanBets on 30/08/2017.
 */
@Module
public class DbModule {
    @Provides
    SplitPotDao provideDb(MainActivity mainActivity){
        //todo provide singleton
        return new SplitPotDaoSQLite(mainActivity.getApplicationContext());
    }
}
