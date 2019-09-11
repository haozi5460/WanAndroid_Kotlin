package com.android.haozi.wanandroid.viewmode

import androidx.lifecycle.ViewModel
import com.android.haozi.wanandroid.repository.ArticleRepository

class ArticleViewMode(val articleRepository: ArticleRepository) : ViewModel(){
    var articleListDataBean = articleRepository.homeArticleList
    var articleCategoryList = articleRepository.articleCategoryList


    fun getArticleCategoryList(){
        articleRepository.getArticleCategoryList()
    }
}