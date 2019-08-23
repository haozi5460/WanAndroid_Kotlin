package com.android.haozi.wanandroid.common

import com.android.haozi.wanandroid.bean.HomeArticleDataBean
import com.android.haozi.wanandroid.bean.UserDataBean
import com.android.haozi.wanandroid.bean.ResponseBean
import io.reactivex.Observable
import retrofit2.http.*

interface WanAndroidAPi {

    @FormUrlEncoded
    @POST(api_account_login)
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<ResponseBean<UserDataBean>>

    @FormUrlEncoded
    @POST(api_account_register)
    fun registerAccount(@Field("username") username: String,
                        @Field("password") password: String,
                        @Field("repassword") repassword: String): Observable<ResponseBean<UserDataBean>>

    @GET(api_account_logout)
    fun logout(): Observable<ResponseBean<UserDataBean>>

    @GET(HomeApi.api_article_list)
    fun getHomeArticleList(@Path("pageIndex") pageIndex: Int): Observable<ResponseBean<HomeArticleDataBean>>
}