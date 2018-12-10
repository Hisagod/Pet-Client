package com.aib.di

import android.app.Application

import com.aib.BaseApplication

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, ActivityModule::class, FragmentModule::class, AppModule::class,ViewModelModule::class))
interface AppComponent {
    fun inject(baseApplication: BaseApplication)
}
