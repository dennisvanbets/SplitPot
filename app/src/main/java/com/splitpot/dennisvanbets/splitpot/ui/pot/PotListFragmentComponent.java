package com.splitpot.dennisvanbets.splitpot.ui.pot;


import com.splitpot.dennisvanbets.splitpot.dao.di.DbModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by DennisVanBets on 30/08/2017.
 */
@Subcomponent(modules = {
        PotListFramentModule.class,
        DbModule.class
})
public interface PotListFragmentComponent extends AndroidInjector<PotListFragment>{
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<PotListFragment>{}
}
