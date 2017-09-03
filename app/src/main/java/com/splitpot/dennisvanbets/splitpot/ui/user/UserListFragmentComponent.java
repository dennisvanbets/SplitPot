package com.splitpot.dennisvanbets.splitpot.ui.user;

import com.splitpot.dennisvanbets.splitpot.dao.di.DbModule;
import com.splitpot.dennisvanbets.splitpot.ui.pot.PotListFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by DennisVanBets on 30/08/2017.
 */

@Subcomponent(modules = {
        UserListActivityModule.class,
        DbModule.class
})
public interface UserListFragmentComponent extends AndroidInjector<UserListFragment>{
    @Subcomponent.Builder public abstract class Builder extends AndroidInjector.Builder<UserListFragment>{}
}
