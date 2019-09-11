package com.android.haozi.wanandroid.paging.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>{
    var dataList: List<T>? = null
    var context: Context
    var onItemClickListener: OnItemClickListener<T>? = null

    constructor(context: Context, dataList: List<T>):super(){
        this.context = context
        this.dataList = dataList
    }

    abstract fun  getViewHolder(view: View) : BaseViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        var view = LayoutInflater.from(context).inflate(getLayoutId(),parent,false)
        var viewHolder = getViewHolder(view)
        viewHolder.onItemViewClickListener = object :
            BaseViewHolder.OnItemViewClickListener<T> {
            override fun onItemViewClick(view: View, position: Int, dataBean: T?) {
                onItemClickListener?.onItemClick(view,position,dataBean)
            }
        }
        return viewHolder
    }

    abstract fun getLayoutId(): Int

    override fun getItemCount(): Int = dataList?.size ?: 0

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.setItemViewData(dataList?.get(position))
    }

    interface OnItemClickListener<T>{
        fun onItemClick(view: View, position: Int, dataBean: T?)
    }
}