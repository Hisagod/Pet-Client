package com.aib.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.aib.pet.R
import com.aib.pet.databinding.ActivityHomeBinding
import com.aib.view.fragment.CenterFragment
import com.aib.view.fragment.VideoFragment
import com.blankj.utilcode.util.ActivityUtils

import java.util.ArrayList

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    private val navigation_head_view = 0    //左侧导航头部View

    private val fragments = ArrayList<Fragment>()

    override fun getResId(): Int = R.layout.activity_home

    override fun initData(savedInstanceState: Bundle?) {

        binding.contr = this

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

        //头部View
        val headerView = binding.nv.getHeaderView(navigation_head_view)
        val iv_head = headerView.findViewById<ImageView>(R.id.iv_head)
        iv_head.setOnClickListener {
            ActivityUtils.startActivity(RegisterActivity::class.java)
        }

        //左侧列表
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

    /**
     * 打开左侧菜单
     */
    fun openLeft(view: View) {
        binding.dl.openDrawer(Gravity.START)
    }
}
