package com.android.haozi.wanandroid.paging.datasource.Factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.android.haozi.wanandroid.paging.datasource.BaseDataSource


abstract class BaseDataSourceFactory<T,M> : DataSource.Factory<T, M>(){
    
    val dataSourceLiveData = MutableLiveData<BaseDataSource<T, M>>()
    
    override fun create(): DataSource<T, M> {
        var dataSource = createDataSource()
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }

    abstract fun createDataSource(): BaseDataSource<T, M>

    open fun invalidateDataSource(cid: String?){}
}