package com.splitpot.dennisvanbets.splitpot.ui.pot;



import android.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Created by DennisVanBets on 30/08/2017.
 */

@Module(subcomponents = PotListFragmentComponent.class)
abstract class PotListFramentModule {
    @Binds
    @IntoMap
    @FragmentKey(PotListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindPotListFrament(PotListFragmentComponent.Builder builder);
}
