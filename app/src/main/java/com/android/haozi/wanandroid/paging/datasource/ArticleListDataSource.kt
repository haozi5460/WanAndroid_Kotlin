package com.android.haozi.wanandroid.paging.datasource

import android.annotation.SuppressLint
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import com.android.haozi.wanandroid.bean.ArticleDataBean
import com.android.haozi.wanandroid.common.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticleListDataSource(var cid: String?) : BaseDataSource<Int,ArticleDataBean>() {
    var wanAndroidAPi = RetrofitManager.init().getWanAndroidAPI()
    var pageIndex: Int = 0
    var topArticleList: List<ArticleDataBean>? = null

    @SuppressLint("CheckResult")
    override fun loadInitData(params: LoadInitialParams<Int>, callback: LoadInitialCallback<ArticleDataBean>) {
        if(cid == null){
            wanAndroidAPi.getArticleTopList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    topArticleList = it.data
                    getArticleList(params,callback)
                    setLoadedState()
                },{
                    retryAgain = {
                        loadInitData(params,callback)
                    }
                    setErrorState(it.message)
                })
        }else{
            getArticleList(params,callback)
        }

    }

    @SuppressLint("CheckResult")
    fun getArticleList(params: LoadInitialParams<Int>,callback: LoadInitialCallback<ArticleDataBean>){
        wanAndroidAPi.getArticleList(pageIndex,this.cid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                pageIndex = it.data.curPage
                var mutableList = it.data.datas.toMutableList()
                topArticleList?.let { it1 -> mutableList.addAll(0, it1) }
                callback.onResult(mutableList)
                setLoadedState()
            },{
                retryAgain = {
                    loadInitData(params,callback)
                }
                setErrorState(it.message)
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
                setLoadedState()
            },{
                retryAgain = {
                    loadNextPageData(params,callback)
                }
                setErrorState(it.message)
            })
    }

    override fun loadBeforePageData(params: LoadParams<Int>, callback: LoadCallback<ArticleDataBean>) {
    }

    override fun getItemKey(item: ArticleDataBean): Int = item.id
}