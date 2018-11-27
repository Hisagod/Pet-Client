package com.aib.net

import com.aib.entity.VideoEntity

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {


    /**
     * 视频列表
     *
     * @return
     */
    @GET("PageSubArea/TrailerList.api")
    fun VIDEO_LIST(): Observable<VideoEntity>

    companion object {
        val videoApi = "http://api.m.mtime.cn/"
    }

}
