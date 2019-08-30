package com.android.haozi.wanandroid.viewmode

import androidx.lifecycle.ViewModel
import com.android.haozi.wanandroid.repository.ArticleRepository

class ArticleViewMode(val articleRepository: ArticleRepository) : ViewModel(){
    var homArticleDataBean = articleRepository.homeArticleList
    var articleCategoryList = articleRepository.articleCategoryList

    fun getHomeArticleList(pageIndex: Int){
        articleRepository.getHomeArticleList(pageIndex)
    }

    fun getArticleCategoryList(){
        articleRepository.getArticleCategoryList()
    }
}