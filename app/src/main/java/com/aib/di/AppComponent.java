package com.aib.di;

import com.aib.base.application.BaseApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, ActivityModule.class, FragmentModule.class, AppModule.class})
public interface AppComponent {
    void inject(BaseApplication baseApplication);
}
