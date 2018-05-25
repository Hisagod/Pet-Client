package com.aib.di;

import com.aib.ui.video.fragment.LocalVideoFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract LocalVideoFragment localVideoFragment();
}
