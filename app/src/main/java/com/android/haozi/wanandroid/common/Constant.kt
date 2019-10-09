package com.android.haozi.wanandroid.common

const val baseUrl = "https://www.wanandroid.com"

const val api_account_login = "/user/login"

const val api_account_register = "/user/register"

const val api_account_logout = "/user/logout/json"

object Constant{
//    val HasUserLogin = "HasUserLogin"
    val UserName = "UserName"
    val UserId = "UserId"
    val ARTICLE_TITLE = "article_title"
    val ARTICLE_URL = "article_url"
}

object HomeApi{
    const val api_article_list = "/article/list/{pageIndex}/json"
    const val api_article_category_list = "/tree/json"
    const val api_article_top_list = "/article/top/json"
}

object CollectApi{
    const val api_collect_list = "/lg/collect/list/{pageIndex}/json"
    const val api_collect_article = "/lg/collect/{articleID}/json"
    const val api_uncollect_article = "/lg/uncollect_originId/{articleID}/json"
}