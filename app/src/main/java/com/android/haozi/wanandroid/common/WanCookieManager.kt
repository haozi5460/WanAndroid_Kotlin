package com.android.haozi.wanandroid.common

import android.content.Context
import android.util.Log
import com.android.haozi.wanandroid.utils.PersistentCookieStore
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.net.URL

class WanCookieManager : CookieJar{
    companion object {
        lateinit var cookieStore: PersistentCookieStore
        fun clearAllCookies(){
            cookieStore.removeAll()
        }

        fun getAllCookieByHost(host: String): MutableList<Cookie>? {
            return cookieStore.getAllCookieByHost(host)
        }
    }

    constructor(context: Context){
        cookieStore = PersistentCookieStore(context)
    }

    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        if(cookies != null && cookies.size > 0 && url.toString().contains(api_account_login)){
            cookies.forEach {
                cookieStore.add(url,it)
            }
        }
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        return cookieStore.get(url)
    }

}