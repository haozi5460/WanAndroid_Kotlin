package com.android.haozi.wanandroid.paging.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.android.haozi.wanandroid.bean.ArticleDataBean

class ArticleListDataSourceFactory(var cid: String?) : DataSource.Factory<Int, ArticleDataBean>() {

    val articleListLiveData = MutableLiveData<ArticleListDataSource>()
    override fun create(): DataSource<Int, ArticleDataBean> {
        var articleListDataSource = createDataSource(cid)
        articleListLiveData.postValue(articleListDataSource)
        return articleListDataSource
    }

    fun createDataSource(cid: String?): ArticleListDataSource{
        return ArticleListDataSource(cid)
    }

    fun invalidateDataSource(cid: String?){
        this.cid = cid
        articleListLiveData.value?.invalidate()
    }
}