package com.splitpot.dennisvanbets.splitpot.dao.di;

import android.app.Application;
import android.util.Log;

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
    private SplitPotDao db;

    @Provides
    SplitPotDao provideDb(Application application){
        if (db == null){
            db = new SplitPotDaoSQLite(application);
        }
        return db;
    }
}
