package com.android.haozi.wanandroid.viewmode

import com.android.haozi.wanandroid.bean.ArticleDataBean
import com.android.haozi.wanandroid.repository.ArticleRepository

class ArticleViewMode(val articleRepository: ArticleRepository) : BasePagingViewModel<Int,ArticleDataBean>(articleRepository){
    var articleCategoryList = articleRepository.articleCategoryList
    var collectDataBean = articleRepository.collectDataBean


    fun getArticleCategoryList(){
        articleRepository.getArticleCategoryList()
    }

    fun collectArticle(dataBean: ArticleDataBean?){
        articleRepository.collectArticle(dataBean)
    }

    fun invalidateDataSource(cid: String?){
        dataSourceFactory?.value?.invalidateDataSource(cid)
    }
}