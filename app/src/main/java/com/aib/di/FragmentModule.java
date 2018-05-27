package com.aib.di;


import com.aib.ui.home.CenterFragment;
import com.aib.ui.home.LocalVideoFragment;
import com.aib.ui.home.VideoFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract LocalVideoFragment localVideoFragment();

    @ContributesAndroidInjector
    abstract CenterFragment centerFragment();

    @ContributesAndroidInjector
    abstract VideoFragment videoFragment();
}
