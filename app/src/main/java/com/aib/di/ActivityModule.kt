package com.aib.di


import com.aib.view.activity.HomeActivity
import com.aib.view.activity.RegisterActivity
import com.aib.view.activity.SettingsActivity
import com.aib.view.activity.SplashActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * 提供Activity对象
 */
@Module
internal abstract class ActivityModule {
    /**
     * 欢迎界面
     */
    @ContributesAndroidInjector
    internal abstract fun SplashActivity(): SplashActivity

    /**
     * 主页
     */
    @ContributesAndroidInjector
    internal abstract fun MainActivity(): HomeActivity

    /**
     * 全部设置
     */
    @ContributesAndroidInjector
    internal abstract fun SettingsActivity(): SettingsActivity

    /**
     * 注册
     */
    @ContributesAndroidInjector
    internal abstract fun RegisterActivity(): RegisterActivity
}
