package com.android.haozi.wanandroid.repository

import androidx.lifecycle.MutableLiveData
import android.text.TextUtils
import com.android.haozi.wanandroid.bean.HomeArticleDataBean
import com.android.haozi.wanandroid.bean.ResponseBean
import com.android.haozi.wanandroid.common.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticleRepository {
    var homeArticleList = MutableLiveData<ResponseBean<HomeArticleDataBean>>()

    fun getHomeArticleList(pageIndex: Int){
        var wanAndroidAPi = RetrofitManager.get().getWanAndroidAPI()

        wanAndroidAPi?.getHomeArticleList(pageIndex)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                homeArticleList.value = it
            },{
                homeArticleList.value = ResponseBean(null as HomeArticleDataBean, 500, it.message)
            })
    }
}