package com.aib.view.activity


import android.os.Bundle
import android.text.TextUtils
import com.aib.pet.R
import com.aib.pet.databinding.ActivityLoginBinding
import com.blankj.utilcode.util.ToastUtils

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun getResId(): Int = R.layout.activity_login

    override fun initData(savedInstanceState: Bundle?) {
        binding.tb.setNavigationOnClickListener {
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val getPhone = binding.etPhone.text.toString().trim()   //获取手机号
            val getPwd = binding.etPwd.text.toString().trim()   //获取密码

            if (TextUtils.isEmpty(getPhone)) {
                ToastUtils.showShort("请输入手机号")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(getPwd)) {
                ToastUtils.showShort("请输入密码")
                return@setOnClickListener
            }

            
        }
    }
}
