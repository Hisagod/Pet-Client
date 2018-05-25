package com.aib.net;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface ApiService {
    String videoApi = "http://api.m.mtime.cn/";

    /**
     * 视频列表
     *
     * @return
     */
    @GET("PageSubArea/TrailerList.api")
    Observable<ResponseBody> VIDEO_LIST();
}
