package com.aib.view.activity

import android.os.Bundle
import com.aib.ll.R
import com.aib.ll.databinding.ActivitySettingsBinding


/**
 * 更多设置
 */
class SettingsActivity : BaseActivity<ActivitySettingsBinding>() {

    override fun getResId(): Int = R.layout.activity_settings

    override fun initData(savedInstanceState: Bundle?) {
        binding.tb.setNavigationOnClickListener {
            finish()
        }
    }
}
