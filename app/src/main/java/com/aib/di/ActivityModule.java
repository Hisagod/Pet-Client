package com.aib.di;


import com.aib.view.activity.MainActivity;
import com.aib.view.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 提供Activity对象
 */
@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();
}
