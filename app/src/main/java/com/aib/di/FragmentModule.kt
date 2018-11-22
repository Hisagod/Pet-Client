package com.aib.di


import com.aib.view.fragment.CenterFragment
import com.aib.view.fragment.VideoFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * 提供Fragment对象
 */
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun centerFragment(): CenterFragment

    @ContributesAndroidInjector
    internal abstract fun videoFragment(): VideoFragment
}
