package com.android.haozi.wanandroid.paging.datasource

import androidx.paging.ItemKeyedDataSource

abstract class BaseDataSource<K, V> : ItemKeyedDataSource<K, V>(){

    override fun loadInitial(params: LoadInitialParams<K>, callback: LoadInitialCallback<V>) {
        loadInitData(params, callback)
    }

    override fun loadAfter(params: LoadParams<K>, callback: LoadCallback<V>) {
        loadNextPageData(params, callback)
    }

    override fun loadBefore(params: LoadParams<K>, callback: LoadCallback<V>) {
        loadBeforePageData(params, callback)
    }

    override fun getKey(item: V): K = getItemKey(item)


    abstract fun loadInitData(params: LoadInitialParams<K>, callback: LoadInitialCallback<V>)

    abstract fun loadNextPageData(params: LoadParams<K>, callback: LoadCallback<V>)

    abstract fun loadBeforePageData(params: LoadParams<K>, callback: LoadCallback<V>)

    abstract fun getItemKey(item: V): K
}