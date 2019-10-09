package com.android.haozi.wanandroid.common

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.android.haozi.wanandroid.paging.NetworkState
import com.android.haozi.wanandroid.paging.datasource.Factory.BaseDataSourceFactory

data class Listing<T,M>(
    val dataSourceFactory: BaseDataSourceFactory<T,M>,
    val pagedList: LiveData<PagedList<M>>,
    val networkState: LiveData<NetworkState>,
    val refreshState: LiveData<NetworkState>,
    val refresh: () -> Unit,
    val retry: () -> Unit)