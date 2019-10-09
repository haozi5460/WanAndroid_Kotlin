package com.android.haozi.wanandroid.repository

import com.android.haozi.wanandroid.common.Listing

interface Repository<T,M>{
    fun getPageDataList(pageSize: Int): Listing<T,M>
}