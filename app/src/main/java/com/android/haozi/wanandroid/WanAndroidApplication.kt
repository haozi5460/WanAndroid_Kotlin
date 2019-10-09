package com.android.haozi.wanandroid

import android.app.Application
import android.content.Context
import com.android.haozi.wanandroid.common.RetrofitManager
import com.android.haozi.wanandroid.utils.PreferenceUtil
import com.facebook.drawee.backends.pipeline.Fresco

class WanAndroidApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        Fresco.initialize(this)
        PreferenceUtil.init(applicationContext)
        RetrofitManager.init()
    }

}
