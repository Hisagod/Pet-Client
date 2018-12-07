package com.aib.net

class Resource<T>(var msg: String?, var status: Status, var data: T?) {
    companion object {

        /**
         * 请求前
         */
        fun <T> loading(): Resource<T> {
            return Resource("数据加载中...", Status.LOADING, null)
        }

        /**
         * 请求成功
         */
        fun <T> success(data: T): Resource<T> {
            return Resource(null, Status.SUCCESS, data)
        }

        /**
         * 请求失败
         */
        fun <T> error(msg: String): Resource<T> {
            return Resource(msg, Status.ERROR, null)
        }


    }
}
