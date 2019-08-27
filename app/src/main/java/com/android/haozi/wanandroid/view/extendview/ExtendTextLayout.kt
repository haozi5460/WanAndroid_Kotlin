package com.android.haozi.wanandroid.view.extendview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.haozi.wanandroid.R
import com.android.haozi.wanandroid.view.extendview.BaseExtendBean
import kotlinx.android.synthetic.main.extend_text_layout.view.*

class ExtendTextLayout : View, View.OnClickListener{
    var titleName: String? = null
    var hasExtendTextLayout: Boolean = false
    var dataList: List<BaseExtendBean>? = null

    constructor(context: Context) : super(context){
//        initView(context, object: AttributeSet())
    }

    constructor(context: Context, attrs: AttributeSet) : super(context,attrs){
        initView(context,attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.extend_text_layout, null)

        var typedArray: TypedArray = context.obtainStyledAttributes(attrs,R.styleable.ExtendTextLayout)

        titleName = typedArray.getString(R.styleable.ExtendTextLayout_title)

        initData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context,LinearLayout.HORIZONTAL,false)
        extend_layout_horizontal_recyclerview.layoutManager = linearLayoutManager
        extend_layout_horizontal_recyclerview.adapter


        extend_layout_vertical_recyclerview.adapter
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
    }

    fun setExtendDataList(dataList: List<BaseExtendBean>?){
        this.dataList = dataList
    }
}