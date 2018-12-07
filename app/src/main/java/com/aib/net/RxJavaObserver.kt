package com.aib.net

import com.blankj.utilcode.util.NetworkUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class RxJavaObserver<T> : Observer<T> {
    override fun onSubscribe(d: Disposable) {
        if (NetworkUtils.isConnected()) {
            onStart("数据加载中...")
        } else {
            onError(ConnectException())
            d.dispose()
        }
    }

    override fun onComplete() {

    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        when (e) {
            is ConnectException -> {
                //数据没打开，没有联网
                onFailure("没有联网")
            }
            is SocketTimeoutException -> {
                //连接超时,服务器关掉
                onFailure("连接超时")
            }

            is NoRouteToHostException -> {
                //请求URL路径找不到,java.net.NoRouteToHostException: No route to host
                onFailure("获取失败")
            }

            is UnknownHostException -> {
//                onFailure("无网络连接")
            }
            else -> {
                onFailure("其它问题")
            }
        }
    }

    abstract fun onStart(s: String)

    abstract fun onSuccess(t: T)

    abstract fun onFailure(s: String)
}