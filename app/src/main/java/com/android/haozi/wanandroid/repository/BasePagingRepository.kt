package com.android.haozi.wanandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.android.haozi.wanandroid.common.Listing
import com.android.haozi.wanandroid.paging.datasource.Factory.BaseDataSourceFactory

abstract class BasePagingRepository<T,M>: Repository<T,M>{
    override fun getPageDataList(pageSize: Int): Listing<T,M> {
        var dataSourceFactory = createDataSourceFactory()

        val pagingConfig = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setPrefetchDistance(50)
            .setEnablePlaceholders(true)
            .build()

        var pagedList: LiveData<PagedList<M>> = LivePagedListBuilder(dataSourceFactory,pagingConfig).build()

        var networkState = Transformations.switchMap(dataSourceFactory.dataSourceLiveData){ it.networkState }

        var refreshState = Transformations.switchMap(dataSourceFactory.dataSourceLiveData) {it.refreshState}

        return Listing(
            dataSourceFactory,
            pagedList,
            networkState,
            refreshState,
            refresh = {
                dataSourceFactory.dataSourceLiveData.value?.refresh
            },
            retry = {
                dataSourceFactory.dataSourceLiveData.value?.invokeRetry()
            }
        )
    }

    abstract fun createDataSourceFactory(): BaseDataSourceFactory<T,M>

}