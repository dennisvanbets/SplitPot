package com.splitpot.dennisvanbets.splitpot.ui.user;



import android.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Created by DennisVanBets on 30/08/2017.
 */

@Module(subcomponents = UserListFragmentComponent.class)
abstract class UserListActivityModule {
    @Binds
    @IntoMap
    @FragmentKey(UserListFragment.class)
    abstract AndroidInjector.Factory<? extends android.app.Fragment>
    bindUserListFrament(UserListFragmentComponent.Builder builder);
}
