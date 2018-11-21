package com.aib.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.aib.loto.R
import com.aib.loto.databinding.ActivityMainBinding
import com.aib.view.fragment.CenterFragment
import com.aib.view.fragment.VideoFragment
import com.blankj.utilcode.util.ActivityUtils

import java.util.ArrayList

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val fragments = ArrayList<Fragment>()

    override fun getResId(): Int = R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {

        fragments.add(VideoFragment())
        fragments.add(CenterFragment())

        binding.bnv.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btn_movie -> {
                    switchFragment(0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.btn_center -> {
                    switchFragment(1)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

        switchFragment(0)

        binding.nv.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.btn_settings -> {
                    ActivityUtils.startActivity(SettingsActivity::class.java)
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    return@setNavigationItemSelectedListener false
                }
            }
        }
    }

    private fun switchFragment(position: Int) {
        val ft = supportFragmentManager.beginTransaction()
        for (i in fragments.indices) {
            val fragment = fragments[i]
            if (i == position) {
                if (fragment.isAdded) {
                    ft.show(fragment)
                } else {
                    ft.add(R.id.fl, fragment)
                }
            } else {
                if (fragment.isAdded) {
                    ft.hide(fragment)
                }
            }
        }
        ft.commit()
    }

}
