package com.aib.view.activity

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.aib.widget.LoadingDialog

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector

abstract class BaseActivity<D : ViewDataBinding> : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var binding: D

    private var loadingDialog: LoadingDialog? = null    //加载对话框

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getResId())

        initData(savedInstanceState)
    }

    abstract fun getResId(): Int

    abstract fun initData(savedInstanceState: Bundle?)

    /**
     * 显示对话框
     */
    fun showDialog() {
        loadingDialog = LoadingDialog()
        loadingDialog!!.isCancelable = false
        loadingDialog!!.show(supportFragmentManager, "loadingDialog")
    }

    /**
     * 取消对话框
     */
    fun dissDialog() {
        if (loadingDialog != null) {
            loadingDialog!!.dismiss()
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return dispatchingAndroidInjector
    }
}
