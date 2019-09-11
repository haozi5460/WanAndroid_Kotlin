package com.android.haozi.wanandroid.paging.datasource

import android.annotation.SuppressLint
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import com.android.haozi.wanandroid.bean.ArticleDataBean
import com.android.haozi.wanandroid.common.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticleListDataSource(var cid: String?) : BaseDataSource<Int,ArticleDataBean>() {
    var wanAndroidAPi = RetrofitManager.get().getWanAndroidAPI()
    var pageIndex: Int = 0

    @SuppressLint("CheckResult")
    override fun loadInitData(params: LoadInitialParams<Int>, callback: LoadInitialCallback<ArticleDataBean>) {
        wanAndroidAPi.getArticleList(pageIndex,this.cid)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                pageIndex = it.data.curPage
                callback.onResult(it.data.datas)
            },{

            })
    }

    @SuppressLint("CheckResult")
    override fun loadNextPageData(params: LoadParams<Int>, callback: LoadCallback<ArticleDataBean>) {
        wanAndroidAPi.getArticleList(pageIndex,this.cid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                pageIndex = it.data?.curPage
                callback.onResult(it.data?.datas)
            },{

            })
    }

    override fun loadBeforePageData(params: LoadParams<Int>, callback: LoadCallback<ArticleDataBean>) {
    }

    override fun getItemKey(item: ArticleDataBean): Int = item.id
}