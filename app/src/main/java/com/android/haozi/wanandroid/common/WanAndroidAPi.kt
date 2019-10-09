package com.android.haozi.wanandroid.common

import com.android.haozi.wanandroid.bean.*
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
    fun getArticleList(@Path("pageIndex") pageIndex: Int, @Query("cid") cid: String?): Observable<ResponseBean<ArticleHomeDataBean>>

    @GET(HomeApi.api_article_category_list)
    fun getArticleCategoryList(): Observable<ResponseBean<List<ArticleCategoryBean>>>

    @GET(HomeApi.api_article_top_list)
    fun getArticleTopList(): Observable<ResponseBean<List<ArticleDataBean>>>

    @POST(CollectApi.api_collect_article)
    fun collectArticleById(@Path("articleID") articleId: Int?): Observable<ResponseBean<String>>

    @POST(CollectApi.api_uncollect_article)
    fun unCollectArticleById(@Path("articleID") articleId: Int?): Observable<ResponseBean<String>>
}