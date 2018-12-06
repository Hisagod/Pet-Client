package com.aib.view.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.text.TextUtils
import com.aib.pet.R
import com.aib.pet.databinding.ActivityRegisterBinding
import com.aib.viewmodel.RegisterViewModel
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.RegexUtils
import com.blankj.utilcode.util.ToastUtils
import javax.inject.Inject

/**
 * 注册
 */
class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    @Inject
    lateinit var vm: RegisterViewModel

    override fun getResId(): Int = R.layout.activity_register

    override fun initData(savedInstanceState: Bundle?) {

        binding.contr = this

        binding.tb.setNavigationOnClickListener {
            finish()
        }

        /**
         * 登录
         */
        binding.tvLogin.setOnClickListener {
            ActivityUtils.startActivity(LoginActivity::class.java)
        }

        binding.btnRegister.setOnClickListener {
            val getPhone = binding.etPhone.text.toString().trim()
            val getPwd = binding.etPwd.text.toString().trim()

            if (TextUtils.isEmpty(getPhone)) {
                ToastUtils.showShort("请输入手机号")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(getPwd)) {
                ToastUtils.showShort("请输入密码")
                return@setOnClickListener
            }

            vm.register(getPhone, EncryptUtils.encryptMD5ToString(getPwd)).observe(this@RegisterActivity, Observer {
                ToastUtils.showLong(it!!.msg)
            })
        }
    }

    /**
     * 当账户EditText文本发生改变
     */
    fun onAccountEditTextChange(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (!RegexUtils.isMobileExact(s)) {
            binding.etAccountLayout.error = "格式错误"
            binding.etAccountLayout.isErrorEnabled = true
        } else {
            binding.etAccountLayout.isErrorEnabled = false
        }
    }
}
