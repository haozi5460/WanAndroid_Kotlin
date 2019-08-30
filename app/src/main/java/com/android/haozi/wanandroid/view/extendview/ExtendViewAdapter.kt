package com.android.haozi.wanandroid.view.extendview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.haozi.wanandroid.R
import kotlinx.android.synthetic.main.extend_view_item_text_layout.view.*

class ExtendViewAdapter : RecyclerView.Adapter<ExtendViewAdapter.ExtendViewHolder> {
    var context: Context? = null
    var dataList: List<BaseExtendBean>? = null
    var onItemViewClickListener: OnItemViewClickListener? = null

    constructor(context: Context) : super(){
        this.context = context
    }


    constructor(context: Context, dataList: List<BaseExtendBean>?): this(context){
        this.context = context
        this.dataList = dataList
    }

    fun updateDataList(dataList: List<BaseExtendBean>?){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtendViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.extend_view_item_text_layout,parent,false)
        var viewHolder = ExtendViewHolder(context,view)
        viewHolder.setOnItemClick(object : ExtendViewHolder.OnItemViewClickListener{
            override fun onItemClick(view: View, position: Int) {
                onItemViewClickListener?.onItemClick(view,position,dataList?.get(position))
                updateDataState(position)
            }
        })
        return viewHolder
    }

    private fun updateDataState(position: Int) {
        dataList?.forEachIndexed { index, baseExtendBean ->
            baseExtendBean.hasChoosed = index == position
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList?.size?:0
    }

    override fun onBindViewHolder(holder: ExtendViewHolder, position: Int) {
        holder.setItemViewData(dataList?.get(position))
    }

    class ExtendViewHolder(context: Context?, view: View): RecyclerView.ViewHolder(view) {
        var context: Context?
        init{
            this.context = context
        }

        fun setItemViewData(dataBean: BaseExtendBean?){
            itemView.extend_item_text.text = dataBean?.name
            var hasChoosed = dataBean?.hasChoosed ?: false
            if(hasChoosed) {
                itemView.setBackgroundResource(R.drawable.extend_itemview_bg)
            }else{
                itemView.setBackgroundColor(ContextCompat.getColor(context!!,android.R.color.transparent))
            }
        }

        fun setOnItemClick(onItemViewClickListener: OnItemViewClickListener){
            itemView.extend_item_text.setOnClickListener {
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