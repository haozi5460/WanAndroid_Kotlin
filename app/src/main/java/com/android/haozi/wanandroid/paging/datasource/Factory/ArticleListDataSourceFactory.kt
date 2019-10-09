package com.android.haozi.wanandroid.paging.datasource.Factory

import com.android.haozi.wanandroid.bean.ArticleDataBean
import com.android.haozi.wanandroid.paging.datasource.ArticleListDataSource
import com.android.haozi.wanandroid.paging.datasource.BaseDataSource

class ArticleListDataSourceFactory(var cid: String?) : BaseDataSourceFactory<Int, ArticleDataBean>() {

    override fun createDataSource(): BaseDataSource<Int, ArticleDataBean> {
        return ArticleListDataSource(cid)
    }

    override fun invalidateDataSource(cid: String?){
        this.cid = cid
        dataSourceLiveData.value?.invalidate()
    }
}