package com.android.haozi.wanandroid.view.extendview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.haozi.wanandroid.R

class ExtendViewAdapter : RecyclerView.Adapter<ExtendViewAdapter.ExtendViewHolder> {
    var context: Context? = null
    var dataList: List<BaseExtendBean>? = null
    var onItemViewClickListener: OnItemViewClickListener? = null

    constructor(context: Context) : super(){
        this.context = context
    }


    constructor(context: Context, dataList: List<BaseExtendBean>): this(context){
        this.context = context
        this.dataList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtendViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.extend_view_item_text_layout,parent,false)
        var viewHolder = ExtendViewHolder(view)
        viewHolder.setOnItemClick(object : ExtendViewHolder.OnItemViewClickListener{
            override fun onItemClick(view: View, position: Int) {
                onItemViewClickListener?.onItemClick(view,position,dataList?.get(position))
            }
        })
        return viewHolder
    }

    override fun getItemCount(): Int {
        return dataList?.size?:0
    }

    override fun onBindViewHolder(holder: ExtendViewHolder, position: Int) {
        holder.setItemViewData(dataList?.get(position))
    }

    class ExtendViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var textView: TextView? = null

        init{
            textView = view.findViewById(R.id.extend_item_text)
        }

        fun setItemViewData(dataBean: BaseExtendBean?){
            textView?.text = dataBean?.name
        }

        fun setOnItemClick(onItemViewClickListener: OnItemViewClickListener){
            textView?.setOnClickListener {
                onItemViewClickListener.onItemClick(it,adapterPosition)
            }
        }

        interface OnItemViewClickListener{
            fun onItemClick(view: View,position: Int)
        }
    }

    interface OnItemViewClickListener{
        fun onItemClick(view:View, position: Int, dataBean: BaseExtendBean?)
    }
}