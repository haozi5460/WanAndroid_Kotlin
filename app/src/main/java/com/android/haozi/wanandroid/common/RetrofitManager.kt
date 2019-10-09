package com.android.haozi.wanandroid.common

import com.android.haozi.wanandroid.WanAndroidApplication
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitManager {
    private var retrofit: Retrofit? = null

    companion object {
        private var instence: RetrofitManager? = null
            get() {
                if (field == null) {
                    field = RetrofitManager();
                }
                return field
            }

        @Synchronized
        fun init(): RetrofitManager{
            return instence!!
        }
    }

    var okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .cache(Cache(File(WanAndroidApplication.context.externalCacheDir.absolutePath+File.separator+"data/WanAndroidCache")
                            , 50*1024*1024))
        .cookieJar(WanCookieManager(WanAndroidApplication.context))
        .connectTimeout(1,TimeUnit.MINUTES)
        .readTimeout(1,TimeUnit.MINUTES)
        .writeTimeout(1,TimeUnit.MINUTES)
        .build()

    constructor(){
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createApi (clazz: Class<*>): Any? {
        return retrofit?.create(clazz)
    }

    fun getWanAndroidAPI(): WanAndroidAPi{
        return init().createApi(WanAndroidAPi::class.java) as WanAndroidAPi
    }

}