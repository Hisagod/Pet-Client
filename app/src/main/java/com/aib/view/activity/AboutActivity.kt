package com.aib.view.activity

import android.databinding.ViewDataBinding
import android.os.Bundle
import com.aib.pet.R

/**
 * 关于APP
 */
class AboutActivity : BaseActivity<ViewDataBinding>() {

    override fun getResId(): Int = R.layout.activity_about

    override fun initData(savedInstanceState: Bundle?) {
    }
}
