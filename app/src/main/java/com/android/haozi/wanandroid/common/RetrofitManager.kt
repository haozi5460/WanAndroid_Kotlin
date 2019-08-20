package com.android.haozi.wanandroid.common

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {
    private var retrofit: Retrofit? = null

    companion object {
        private var instence: RetrofitManager? = null
            get() {
                if (field == null) {
                    field = RetrofitManager();
                }
                return field;
            }

        @Synchronized
        fun get(): RetrofitManager{
            return instence!!;
        }
    }

    constructor(){
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createApi (clazz: Class<*>): Any? {
        return retrofit?.create(clazz)
    }

    fun getWanAndroidAPI(): WanAndroidAPi{
        return get().createApi(WanAndroidAPi::class.java) as WanAndroidAPi
    }

}