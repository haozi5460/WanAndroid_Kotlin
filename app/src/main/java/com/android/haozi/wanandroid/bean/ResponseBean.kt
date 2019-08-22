package com.android.haozi.wanandroid.bean

data class ResponseBean<T> (var data : T, var errorCode: Int = 0, var errorMsg: String? = null)