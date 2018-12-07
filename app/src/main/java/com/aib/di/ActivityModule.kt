package com.aib.di


import com.aib.view.activity.*
import com.aib.view.activity.RegisterActivity

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
    abstract fun SplashActivity(): SplashActivity

    /**
     * 主页
     */
    @ContributesAndroidInjector
    abstract fun MainActivity(): HomeActivity

    /**
     * 全部设置
     */
    @ContributesAndroidInjector
    abstract fun SettingsActivity(): SettingsActivity

    /**
     * 注册
     */
    @ContributesAndroidInjector
    abstract fun RegisterActivity(): RegisterActivity

    /**
     * 登录
     */
    @ContributesAndroidInjector
    abstract fun LoginActivity(): LoginActivity

    /**
     * 关于app
     */
    @ContributesAndroidInjector
    abstract fun AboutActivity(): AboutActivity
}
