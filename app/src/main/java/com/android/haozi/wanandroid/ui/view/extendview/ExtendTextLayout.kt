package com.android.haozi.wanandroid.ui.view.extendview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.haozi.wanandroid.R
import kotlinx.android.synthetic.main.extend_text_layout.view.*

class ExtendTextLayout : FrameLayout, View.OnClickListener, ExtendViewAdapter.OnItemViewClickListener{
    var titleName: String? = null
    var hasExtendTextLayout: Boolean = false
    var dataList: List<BaseExtendBean>? = null
    var extendViewAdapter: ExtendViewAdapter? = null
    var onExtendItemClickListener: OnExtendItemClickListener<BaseExtendBean>? = null

    constructor(context: Context) : super(context){
//        initView(context, object: AttributeSet())
    }

    constructor(context: Context, attrs: AttributeSet) : super(context,attrs){
        initView(context,attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.extend_text_layout, this)

        var typedArray: TypedArray = context.obtainStyledAttributes(attrs,R.styleable.ExtendTextLayout)

        titleName = typedArray.getString(R.styleable.ExtendTextLayout_title)
        typedArray.recycle()
        initData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context,LinearLayout.HORIZONTAL,false)
        extend_layout_horizontal_recyclerview.layoutManager = linearLayoutManager
        extendViewAdapter = ExtendViewAdapter(context,dataList)
        extend_layout_horizontal_recyclerview.adapter = extendViewAdapter
        extendViewAdapter?.onItemViewClickListener = this


        var gridLayoutManager = StaggeredGridLayoutManager(4, RecyclerView.VERTICAL)
        extend_layout_vertical_recyclerview.adapter = extendViewAdapter
        extend_layout_vertical_recyclerview.layoutManager = gridLayoutManager
    }

    private fun initData() {
        extend_layout_title.text = titleName
        extend_layout_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.extend_layout_btn -> {
                doExtendTextLayout()
            }
        }
    }

    private fun doExtendTextLayout() {
        if(hasExtendTextLayout){
            hasExtendTextLayout = false
            extend_layout_btn?.setImageResource(R.drawable.ic_down)
        }else{
            hasExtendTextLayout = true
            extend_layout_btn?.setImageResource(R.drawable.ic_up)
        }
        extend_layout_horizontal_recyclerview.visibility = if(hasExtendTextLayout) GONE else VISIBLE
        extend_layout_vertical_recyclerview.visibility = if(hasExtendTextLayout) VISIBLE else GONE
    }

    fun setExtendDataList(dataList: List<BaseExtendBean>?){
//        this.dataList = dataList
        dataList?.forEachIndexed { index, baseExtendBean ->
            baseExtendBean.hasChoosed = if(index == 0) true else false
        }
        extendViewAdapter?.updateDataList(dataList)
    }

    override fun onItemClick(view: View, position: Int, dataBean: BaseExtendBean?) {
        onExtendItemClickListener?.onExtendItemClick(view,dataBean)
    }
}