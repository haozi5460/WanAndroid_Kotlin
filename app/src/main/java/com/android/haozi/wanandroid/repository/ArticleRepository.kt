package com.android.haozi.wanandroid.repository

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import android.util.Log
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.android.haozi.wanandroid.bean.ArticleCategoryBean
import com.android.haozi.wanandroid.bean.ArticleDataBean
import com.android.haozi.wanandroid.bean.ArticleHomeDataBean
import com.android.haozi.wanandroid.bean.ResponseBean
import com.android.haozi.wanandroid.common.RetrofitManager
import com.android.haozi.wanandroid.paging.datasource.ArticleListDataSourceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticleRepository(var cid: String?) {
    var articleCategoryList = MutableLiveData<ResponseBean<List<ArticleCategoryBean>>>()

    var articleListDataSource = ArticleListDataSourceFactory(cid)
    val articleListPagingConfig = PagedList.Config.Builder()
        .setPageSize(20)
        .setPrefetchDistance(50)
        .setEnablePlaceholders(true)
        .build()
    var homeArticleList = LivePagedListBuilder<Int,ArticleDataBean>(articleListDataSource,articleListPagingConfig).build()

    fun invalidateDataSource(cid: String?){
        articleListDataSource.invalidateDataSource(cid)
    }


    @SuppressLint("CheckResult")
    fun getArticleCategoryList(){
        var wanAndroidAPi = RetrofitManager.get().getWanAndroidAPI()
        wanAndroidAPi?.getArticleCategoryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                articleCategoryList.value = it
                Log.e("shihao","articleCategorylist =="+it.data?.get(0).toString())
            },{
                Log.e("shihao","articleCategorylist =="+articleCategoryList)
                Log.e("shihao","articleCategorylist =="+articleCategoryList.value.toString())
                var listData = listOf(ArticleCategoryBean(null,-1,-1,0,-1,false,-1,null,false))
                articleCategoryList.value = ResponseBean(listData,500,it.message)
            })
    }
}