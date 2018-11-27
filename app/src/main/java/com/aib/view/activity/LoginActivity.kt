package com.aib.view.activity


import android.os.Bundle
import com.aib.ll.R
import com.aib.ll.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun getResId(): Int = R.layout.activity_login

    override fun initData(savedInstanceState: Bundle?) {
        binding.tb.setNavigationOnClickListener {
            finish()
        }


    }
}
