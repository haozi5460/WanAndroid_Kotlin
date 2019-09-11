package com.android.haozi.wanandroid.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.haozi.wanandroid.R
import com.android.haozi.wanandroid.bean.ArticleCategoryBean
import com.android.haozi.wanandroid.paging.adapter.ArticleListAdapter
import com.android.haozi.wanandroid.paging.datasource.ArticleListDataSourceFactory
import com.android.haozi.wanandroid.repository.ArticleRepository
import com.android.haozi.wanandroid.ui.view.extendview.BaseExtendBean
import com.android.haozi.wanandroid.ui.view.extendview.OnExtendItemClickListener
import com.android.haozi.wanandroid.viewmode.ArticleViewMode
import kotlinx.android.synthetic.main.main_home_page_layout.*

class HomeFragment : BaseFragment() {
    private lateinit var articleViewMode: ArticleViewMode
    private lateinit var articleListAdapter: ArticleListAdapter
    private var articleRepository = ArticleRepository(null)

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
        initArticleListAdapter()
        getArticleCategoryList()
    }

    private fun initArticleListAdapter() {
        articleListAdapter = ArticleListAdapter(context!!)
        main_home_recyclerview.layoutManager = LinearLayoutManager(context)
        main_home_recyclerview.adapter = articleListAdapter
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser){
            getArticleCategoryList()
        }
    }

    private fun initView() {
        home_page_category_extend?.onExtendItemClickListener = onExtendItemClickListener
        home_page_type_extend.onExtendItemClickListener = onExtendItemClickListener
        main_home_swipe_refresh.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW)
        main_home_swipe_refresh.setOnRefreshListener {
        }
    }

    private fun initViewMode() {
        articleViewMode = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ArticleViewMode(articleRepository) as T
            }

        })[ArticleViewMode::class.java]

        articleViewMode.articleCategoryList?.observe(this, Observer {
            if(it.errorCode == 500){
                Toast.makeText(context,it.errorMsg,Toast.LENGTH_SHORT).show()
            }else {
                home_page_category_extend.setExtendDataList(getAddCategoryList(it.data))
            }
        })

        articleViewMode.articleListDataBean?.observe(this, Observer {
            articleListAdapter.submitList(it)
            main_home_recyclerview.scrollToPosition(0)
        })
    }

    private fun getAddCategoryList(data: List<ArticleCategoryBean>): List<ArticleCategoryBean>? {
        var sonArticleCategoryBean = ArticleCategoryBean(null,-1,-1,0,-1
            ,false,-1,"最新博文",false)
        var articleCategoryBean = ArticleCategoryBean(
            arrayListOf(sonArticleCategoryBean),-1,-1,0,-1
            ,false,-1,"最新博文",false)

        var mutableDataList = data.toMutableList()
        mutableDataList.add(0,articleCategoryBean)
        return mutableDataList
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
                    if(dataBean.id == -1){
                        articleRepository.invalidateDataSource(null)
                    }else {
                        articleRepository.invalidateDataSource(dataBean.id.toString())
                    }
                }
             }
         }

     }

}