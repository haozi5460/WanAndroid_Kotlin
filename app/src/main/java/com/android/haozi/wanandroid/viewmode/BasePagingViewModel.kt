package com.android.haozi.wanandroid.viewmode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.android.haozi.wanandroid.repository.Repository

open class BasePagingViewModel<T,M>(repository: Repository<T,M>): ViewModel(){

    val pageSizeData = MutableLiveData<Int>()

    var resultDataListing = Transformations.map(pageSizeData){ repository.getPageDataList(it)}

    var pagedList = Transformations.switchMap(resultDataListing){  it.pagedList }

    var networkState = Transformations.switchMap(resultDataListing){ it.networkState }

    var refreshState = Transformations.switchMap(resultDataListing){ it.refreshState }

    var dataSourceFactory = Transformations.map(resultDataListing) {
        it.dataSourceFactory
    }

    fun refresh(){
        resultDataListing.value?.refresh?.invoke()
    }

    fun retryAgain(){
        resultDataListing.value?.retry?.invoke()
    }

    fun setPagedSize(int: Int = 20){
        if(pageSizeData.value != int){
            pageSizeData.value = int
        }
    }
}
