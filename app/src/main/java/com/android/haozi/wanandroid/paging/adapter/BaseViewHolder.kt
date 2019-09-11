package com.android.haozi.wanandroid.paging.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T>: RecyclerView.ViewHolder{
    var view: View
    var onItemViewClickListener: OnItemViewClickListener<T>? = null

    constructor(view: View) : super(view){
        this.view = view
    }

    open fun setItemViewData(dataBean: T?){
        view.setOnClickListener {
            onItemViewClickListener?.onItemViewClick(view, adapterPosition, dataBean)
        }
    }

    interface OnItemViewClickListener<T>{
        fun onItemViewClick(view: View, position: Int, dataBean: T?)
    }
}
