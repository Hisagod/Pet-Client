package com.aib.di;


import com.aib.ui.home.MainActivity;
import com.aib.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity mainActivity();
}
