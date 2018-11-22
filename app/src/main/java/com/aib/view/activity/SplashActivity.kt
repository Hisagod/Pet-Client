package com.aib.view.activity

import android.databinding.ViewDataBinding
import android.os.Bundle

import com.aib.ll.R
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils


class SplashActivity : BaseActivity<ViewDataBinding>() {
    private val permissions = arrayOf("android.permission.READ_EXTERNAL_STORAGE")

    override fun getResId(): Int {
        return R.layout.activity_splash
    }

    override fun initData(savedInstanceState: Bundle?) {

        requestPermission()
    }

    private fun requestPermission() {
        PermissionUtils.permission(*permissions).callback(object : PermissionUtils.SimpleCallback {
            override fun onGranted() {
                ActivityUtils.startActivity(HomeActivity::class.java)
                finish()
            }

            override fun onDenied() {
                ToastUtils.showShort("拒绝授权")
                finish()
            }
        }).request()
    }
}
