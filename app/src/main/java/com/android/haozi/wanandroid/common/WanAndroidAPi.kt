package com.android.haozi.wanandroid.common

import com.android.haozi.wanandroid.bean.DataBean
import com.android.haozi.wanandroid.bean.ResponseBean
import io.reactivex.Observable
import retrofit2.http.*

interface WanAndroidAPi {

    @FormUrlEncoded
    @POST(api_account_login)
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<ResponseBean<DataBean>>

    @FormUrlEncoded
    @POST(api_account_register)
    fun registerAccount(@Field("username") username: String,
                        @Field("password") password: String,
                        @Field("repassword") repassword: String): Observable<ResponseBean<DataBean>>

    @GET(api_account_logout)
    fun logout(): Observable<ResponseBean<DataBean>>
}