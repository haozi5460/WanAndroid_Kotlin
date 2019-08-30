package com.android.haozi.wanandroid.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.android.haozi.wanandroid.paging.adapter.BaseViewHolder

abstract class BasePagedListAdapter<T> : PagedListAdapter<T,BaseViewHolder<T>>{
    lateinit var context: Context
    var onItemClickListener: OnItemClickListener<T>? = null

    constructor(context: Context, diffCallback: ItemCallback<T>):super(diffCallback){
        this.context = context
    }

    abstract fun getLayoutId(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        var view = LayoutInflater.from(context).inflate(getLayoutId(),parent,false)
        var viewHolder = BaseViewHolder<T>(view)
        viewHolder.onItemViewClickListener = object :
            BaseViewHolder.OnItemViewClickListener<T> {
            override fun onItemViewClick(view: View, position: Int, dataBean: T?) {
                onItemClickListener?.onItemClick(view,position,dataBean)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.setItemViewData(getItem(position))
    }

    interface OnItemClickListener<T>{
        fun onItemClick(view: View, position: Int, dataBean: T?)
    }
}
