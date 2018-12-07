package com.aib.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.aib.entity.BaseEntity
import com.aib.net.ApiService
import com.aib.net.Resource
import com.aib.net.RxJavaObserver
import com.blankj.utilcode.util.LogUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * 注册
 */
class RegisterViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var apiService: ApiService

    /**
     * 注册
     */
    fun register(phone: String, pwd: String): MutableLiveData<Resource<BaseEntity<String>>> {
        val data = MutableLiveData<Resource<BaseEntity<String>>>()
        apiService
                .USER_REGISTER(phone, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//
//                },{
//                    LogUtils.e(it.toString())
//                })
                .subscribe(object : RxJavaObserver<BaseEntity<String>>() {
                    override fun onStart(s: String) {
                        data.value = Resource.loading()
                    }

                    override fun onSuccess(t: BaseEntity<String>) {
                        data.value = Resource.success(t)
                    }

                    override fun onFailure(s: String) {
                        data.value = Resource.error(s)
                    }
                })
        return data
    }
}