package com.aib.view.activity

import android.os.Bundle
import android.view.View
import com.aib.pet.R
import com.aib.pet.databinding.ActivitySettingsBinding
import com.blankj.utilcode.util.ActivityUtils


/**
 * 更多设置
 */
class SettingsActivity : BaseActivity<ActivitySettingsBinding>() {

    override fun getResId(): Int = R.layout.activity_settings

    override fun initData(savedInstanceState: Bundle?) {

        binding.contr = this

        binding.tb.setNavigationOnClickListener {
            finish()
        }
    }

    /**
     * 进入关于app
     */
    fun about(view: View) {
        ActivityUtils.startActivity(AboutActivity::class.java)
    }
}
