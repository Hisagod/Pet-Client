package com.aib.binding

import android.databinding.BindingAdapter
import android.view.View
import com.aib.net.Resource
import com.aib.net.Status
import com.aib.view.activity.BaseActivity
import com.blankj.utilcode.util.ToastUtils

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("showDialog")
    fun showDialog(view: View, resource: Resource<*>?) {
        /**
         * 需要判断不为null，因为这个自定义属性是放在布局跟目录，一开始加载就会执行
         */
        if (resource != null) {
            val activity = view.context as BaseActivity<*>
            when (resource.status) {
                Status.LOADING -> {
                    activity.showDialog()
                }
                Status.SUCCESS -> {
                    activity.dissDialog()
                }
                Status.ERROR -> {
                    activity.dissDialog()
                    ToastUtils.showShort(resource.msg)
                }
            }
        }
    }
}
