package com.aib.di;


import com.aib.ui.other.activity.MainActivity;
import com.aib.ui.other.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity mainActivity();
}
