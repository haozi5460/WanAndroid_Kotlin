package com.android.haozi.wanandroid.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.android.haozi.wanandroid.R
import com.android.haozi.wanandroid.bean.ArticleCategoryBean
import com.android.haozi.wanandroid.repository.ArticleRepository
import com.android.haozi.wanandroid.view.extendview.BaseExtendBean
import com.android.haozi.wanandroid.view.extendview.ExtendViewAdapter
import com.android.haozi.wanandroid.view.extendview.OnExtendItemClickListener
import com.android.haozi.wanandroid.viewmode.ArticleViewMode
import kotlinx.android.synthetic.main.main_home_page_layout.*

class HomeFragment : BaseFragment() {
    private lateinit var articleViewMode: ArticleViewMode

    companion object {
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }

    override fun getLayoutId() = R.layout.main_home_page_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewMode()
        getArticleCategoryList()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser){
            getArticleCategoryList()
        }
    }

    private fun initView() {
        home_page_category_extend?.onExtendItemClickListener = onExtendItemClickListener
    }

    private fun initViewMode() {
        articleViewMode = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ArticleViewMode(ArticleRepository()) as T
            }

        })[ArticleViewMode::class.java]

        articleViewMode.articleCategoryList?.observe(this, Observer {
            home_page_category_extend.setExtendDataList(it.data)
        })
    }

    private fun getArticleCategoryList() {
        articleViewMode.getArticleCategoryList()
    }

     var onExtendItemClickListener = object : OnExtendItemClickListener<BaseExtendBean> {
         override fun onExtendItemClick(view: View, dataBean: BaseExtendBean?) {
             if(dataBean is ArticleCategoryBean){
                if(dataBean.children?.size ?: 0 > 0){//subCategory
                    home_page_type_extend.setExtendDataList(dataBean.children)
                }else{//show articleList for subCategory

                }
             }
         }

     }

}