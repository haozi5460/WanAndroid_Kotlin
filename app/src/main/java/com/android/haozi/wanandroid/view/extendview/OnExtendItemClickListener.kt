package com.android.haozi.wanandroid.view.extendview

import android.view.View

interface OnExtendItemClickListener<T>{
    fun onExtendItemClick(view: View, dataBean: T?)
}