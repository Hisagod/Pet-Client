package com.aib.view.activity

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.os.CountDownTimer
import android.view.KeyEvent

import com.aib.pet.R
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils

/**
 * 欢迎页面
 */
class SplashActivity : BaseActivity<ViewDataBinding>() {
    private var timer: CountDownTimer? = null

    override fun getResId(): Int = R.layout.activity_splash

    override fun initData(savedInstanceState: Bundle?) {
        requestPermission()
    }

    private fun requestPermission() {
        PermissionUtils.permission().callback(object : PermissionUtils.SimpleCallback {
            override fun onGranted() {
                timer = object : CountDownTimer(3000, 1000) {
                    override fun onFinish() {
                        ActivityUtils.startActivity(HomeActivity::class.java)
                        finish()
                    }

                    override fun onTick(millisUntilFinished: Long) {

                    }
                }.start()
            }

            override fun onDenied() {
                ToastUtils.showShort("拒绝授权")
                finish()
            }
        }).request()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> timer!!.cancel()
        }
        return super.onKeyDown(keyCode, event)
    }
}
