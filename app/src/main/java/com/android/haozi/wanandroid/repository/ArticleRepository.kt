package com.android.haozi.wanandroid.repository

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import android.text.TextUtils
import android.util.Log
import com.android.haozi.wanandroid.bean.ArticleCategoryBean
import com.android.haozi.wanandroid.bean.HomeArticleDataBean
import com.android.haozi.wanandroid.bean.ResponseBean
import com.android.haozi.wanandroid.common.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticleRepository {
    var homeArticleList = MutableLiveData<ResponseBean<HomeArticleDataBean>>()
    var articleCategoryList = MutableLiveData<ResponseBean<List<ArticleCategoryBean>>>()

    @SuppressLint("CheckResult")
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

    @SuppressLint("CheckResult")
    fun getArticleCategoryList(){
        var wanAndroidAPi = RetrofitManager.get().getWanAndroidAPI()
        wanAndroidAPi?.getArticleCategoryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                articleCategoryList.value = it
                Log.e("shihao","articleCategorylist =="+it.data.get(0).toString())
            },{
                articleCategoryList.value = ResponseBean(null as List<ArticleCategoryBean>,500,it.message)
                Log.e("shihao","articleCategorylist =="+articleCategoryList.value.toString())
            })
    }
}