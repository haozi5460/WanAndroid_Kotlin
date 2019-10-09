package com.android.haozi.wanandroid.paging.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.android.haozi.wanandroid.paging.NetworkState
import java.util.concurrent.Executors

abstract class BaseDataSource<K, V> : ItemKeyedDataSource<K, V>(){
    val retryExecutor = Executors.newFixedThreadPool(5)!!
    var retryAgain: (() -> Any)? = null

    var refresh: (() -> Any)? = null

    val networkState = MutableLiveData<NetworkState>()
    val refreshState = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<K>, callback: LoadInitialCallback<V>) {
        refreshState.postValue(NetworkState.LOADING)
        loadInitData(params, callback)
    }

    override fun loadAfter(params: LoadParams<K>, callback: LoadCallback<V>) {
        setLoadingState()
        loadNextPageData(params, callback)
    }

    override fun loadBefore(params: LoadParams<K>, callback: LoadCallback<V>) {
        loadBeforePageData(params, callback)
    }

    override fun getKey(item: V): K = getItemKey(item)

    fun setLoadingState(){
        retryAgain = null
        refreshState.postValue(NetworkState.LOADING)
        networkState.postValue(NetworkState.LOADING)
    }

    fun setLoadedState(){
        retryAgain = null
        refreshState.postValue(NetworkState.LOADED)
        networkState.postValue(NetworkState.LOADED)
    }

    fun setNetworkStateLoaded(){
        retryAgain = null
        networkState.postValue(NetworkState.LOADED)
    }

    fun setErrorState(msg: String?){
        refreshState.postValue(NetworkState.error(msg))
        networkState.postValue(NetworkState.error(msg))
    }

    fun invokeRetry(){
        val retry = retryAgain
        retryAgain = null
        retry.let {
            retryExecutor.execute{
                it?.invoke()
            }
        }

    }

    abstract fun loadInitData(params: LoadInitialParams<K>, callback: LoadInitialCallback<V>)

    abstract fun loadNextPageData(params: LoadParams<K>, callback: LoadCallback<V>)

    abstract fun loadBeforePageData(params: LoadParams<K>, callback: LoadCallback<V>)

    abstract fun getItemKey(item: V): K
}