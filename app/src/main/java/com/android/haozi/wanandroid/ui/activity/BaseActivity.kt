package com.android.haozi.wanandroid.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import butterknife.ButterKnife

abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayoutId():Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
//        ButterKnife.bind(this)
    }
}