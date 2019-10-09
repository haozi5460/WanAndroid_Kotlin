package com.android.haozi.wanandroid.repository

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.android.haozi.wanandroid.bean.ArticleCategoryBean
import com.android.haozi.wanandroid.bean.ArticleDataBean
import com.android.haozi.wanandroid.bean.ResponseBean
import com.android.haozi.wanandroid.common.RetrofitManager
import com.android.haozi.wanandroid.paging.datasource.Factory.ArticleListDataSourceFactory
import com.android.haozi.wanandroid.paging.datasource.Factory.BaseDataSourceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticleRepository(var cid: String?): BasePagingRepository<Int, ArticleDataBean>() {

    var articleCategoryList = MutableLiveData<ResponseBean<List<ArticleCategoryBean>>>()
    var collectDataBean = MutableLiveData<ResponseBean<String>>()

    override fun createDataSourceFactory(): BaseDataSourceFactory<Int, ArticleDataBean> {
        return ArticleListDataSourceFactory(cid)
    }


    @SuppressLint("CheckResult")
    fun getArticleCategoryList(){
        var wanAndroidAPi = RetrofitManager.init().getWanAndroidAPI()
        wanAndroidAPi?.getArticleCategoryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                articleCategoryList.value = it
            },{
                var listData = listOf(ArticleCategoryBean(null,-1,-1,0,-1,false,-1,null,false))
                articleCategoryList.value = ResponseBean(listData,500,it.message)
            })
    }

    @SuppressLint("CheckResult")
    fun collectArticle(dataBean: ArticleDataBean?){
        var wanAndroidAPi = RetrofitManager.init().getWanAndroidAPI()
        var collectObservable = if(dataBean?.collect?:false) wanAndroidAPi.collectArticleById(dataBean?.id) else wanAndroidAPi.unCollectArticleById(dataBean?.id)
        collectObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.data = if(dataBean?.collect?:false) "收藏成功！" else "取消收藏成功！"
                collectDataBean.value = it
            },{
                collectDataBean.value = ResponseBean("",500,it.message)
            })
    }
}