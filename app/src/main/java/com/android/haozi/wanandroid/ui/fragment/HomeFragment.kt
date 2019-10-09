package com.android.haozi.wanandroid.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.haozi.wanandroid.R
import com.android.haozi.wanandroid.bean.ArticleCategoryBean
import com.android.haozi.wanandroid.bean.ArticleDataBean
import com.android.haozi.wanandroid.common.Constant
import com.android.haozi.wanandroid.paging.adapter.ArticleListAdapter
import com.android.haozi.wanandroid.paging.adapter.BasePagedListAdapter
import com.android.haozi.wanandroid.paging.Status
import com.android.haozi.wanandroid.repository.ArticleRepository
import com.android.haozi.wanandroid.ui.activity.ArticleDetailActiviy
import com.android.haozi.wanandroid.ui.view.extendview.BaseExtendBean
import com.android.haozi.wanandroid.ui.view.extendview.OnExtendItemClickListener
import com.android.haozi.wanandroid.viewmode.ArticleViewMode
import kotlinx.android.synthetic.main.main_home_page_layout.*
import kotlin.math.log

class HomeFragment : BaseFragment() {

    private lateinit var articleViewMode: ArticleViewMode
    private lateinit var articleListAdapter: ArticleListAdapter
    private var articleCid: String? = null
    private var hasLoadedCategory: Boolean = false

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
        articleListAdapter.onItemClickListener = onArticleItemClickListener
        articleListAdapter.onArticleCollectListener = onArticleCollectListener
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
            if(hasLoadedCategory) {
                articleViewMode.invalidateDataSource(articleCid)
            }else{
                getArticleCategoryList()
            }
        }

        try_again.setOnClickListener {
            if(hasLoadedCategory) {
                articleViewMode.retryAgain()
            }else{
                getArticleCategoryList()
            }
        }
    }

    private fun initViewMode() {
        articleViewMode = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ArticleViewMode(ArticleRepository(null)) as T
            }

        })[ArticleViewMode::class.java]

        articleViewMode.setPagedSize(20)

        articleViewMode.articleCategoryList.observe(this, Observer {
            if(it.errorCode == 500){
                hasLoadedCategory = false
                main_home_swipe_refresh.isRefreshing = false
                Toast.makeText(context,it.errorMsg,Toast.LENGTH_SHORT).show()
            }else {
                hasLoadedCategory = true
                home_page_category_extend.setExtendDataList(getAddCategoryList(it.data))
            }
        })

        articleViewMode.pagedList.observe(this, Observer {
            main_home_recyclerview.visibility = View.VISIBLE
            try_again.visibility = View.GONE
            articleListAdapter.submitList(it)
            main_home_recyclerview.scrollToPosition(0)
        })

        articleViewMode.collectDataBean.observe(this, Observer {
            if(it.errorCode == 500){
                Toast.makeText(context,it.errorMsg,Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,it.data,Toast.LENGTH_SHORT).show()
            }
        })

        articleViewMode.refreshState.observe(this, Observer {
            main_home_swipe_refresh.isRefreshing = it.status == Status.RUNNING
        })

        articleViewMode.dataSourceFactory.observe(this, Observer {
            Log.e("shihao","IT=="+it)
        })

        articleViewMode.networkState.observe(this, Observer {
            if(it.status == Status.FAILED){
                main_home_recyclerview.visibility = View.GONE
                try_again.visibility = View.VISIBLE
                Toast.makeText(context,it.msg,Toast.LENGTH_SHORT).show()
            }else if(it.status == Status.SUCCESS){
                main_home_recyclerview.visibility = View.VISIBLE
                try_again.visibility = View.GONE
            }
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
                    articleCid = if(dataBean.id == -1) null else dataBean.id.toString()
                    articleViewMode.invalidateDataSource(articleCid)
                }
             }
         }

     }

    var onArticleItemClickListener = object: BasePagedListAdapter.OnItemClickListener<ArticleDataBean>{

        override fun onItemClick(view: View, position: Int, dataBean: ArticleDataBean?) {
            var intent = Intent(activity, ArticleDetailActiviy::class.java)
            var bundle = Bundle()
            bundle.putString(Constant.ARTICLE_TITLE, dataBean?.title)
            bundle.putString(Constant.ARTICLE_URL, dataBean?.link)
            intent.putExtras(bundle)
            activity?.startActivity(intent)
        }
    }

    var onArticleCollectListener = object: ArticleListAdapter.OnArticleCollectListener<ArticleDataBean>{
        override fun onArticleCollectClick(dataBean: ArticleDataBean?) {
            articleViewMode.collectArticle(dataBean)
        }
    }
}