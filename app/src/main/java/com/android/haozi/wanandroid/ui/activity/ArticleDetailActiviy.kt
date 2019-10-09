package com.android.haozi.wanandroid.ui.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import android.view.View
import android.webkit.*
import com.android.haozi.wanandroid.R
import com.android.haozi.wanandroid.common.Constant
import kotlinx.android.synthetic.main.article_detail_web_layout.*

class ArticleDetailActiviy : BaseActivity() {
    lateinit var articleTitle: String
    lateinit var articleUrl: String
    override fun getLayoutId(): Int = R.layout.article_detail_web_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArticleDetailInfo()
        initToolbar()
        initWebView()
        showArticleDetail()
    }

    private fun initWebView() {
        var webSettings = article_detail_webview.settings
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webSettings.allowFileAccess = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.loadsImagesAutomatically = true
        webSettings.defaultTextEncodingName = "utf-8"

        article_detail_webview.webViewClient = object: WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                webvew_progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                webvew_progressBar.visibility = View.GONE
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }

        article_detail_webview.webChromeClient = object: WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                webvew_progressBar.progress = newProgress
            }
        }
    }

    private fun showArticleDetail() {
        article_detail_webview.loadUrl(articleUrl)
    }

    private fun initToolbar() {
        article_detail_toolbar.setNavigationOnClickListener {
            onWebBackPressed()
        }
        article_detail_toolbar_title.text = articleTitle
    }

    private fun getArticleDetailInfo() {
        if(intent != null){
            var bundle = intent.extras
            articleTitle = bundle.getString(Constant.ARTICLE_TITLE)
            articleUrl = bundle.getString(Constant.ARTICLE_URL)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KEYCODE_BACK && article_detail_webview.canGoBack()) {
            article_detail_webview.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun onWebBackPressed() {
        if(article_detail_webview.canGoBack()){
            article_detail_webview.goBack()
        }else{
            finish()
        }
    }
}