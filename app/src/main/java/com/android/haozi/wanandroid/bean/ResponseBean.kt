package com.android.haozi.wanandroid.bean

data class ResponseBean<T> (var dataBean : T, var errorCOde: Int = 0, var errorMsg: String? = null)