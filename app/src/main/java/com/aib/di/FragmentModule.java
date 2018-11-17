package com.aib.di;


import com.aib.view.fragment.CenterFragment;
import com.aib.view.fragment.LocalVideoFragment;
import com.aib.view.fragment.VideoFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 提供Fragment对象
 */
@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract LocalVideoFragment localVideoFragment();

    @ContributesAndroidInjector
    abstract CenterFragment centerFragment();

    @ContributesAndroidInjector
    abstract VideoFragment videoFragment();
}
