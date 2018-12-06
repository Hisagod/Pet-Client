package com.aib.net

import com.aib.entity.BaseEntity
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {
    /**
     * 用户注册
     */
    @POST("user/register")
    @FormUrlEncoded
    fun USER_REGISTER(@Field("phone") phone: String, @Field("pwd") pwd: String): Observable<BaseEntity<String>>
}
