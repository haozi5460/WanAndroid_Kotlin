package com.android.haozi.wanandroid.lifecycle

import android.app.AppComponentFactory
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LifecycleService

class LifecycleActivity : AppCompatActivity(), LifecycleOwner{

//    var lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
//    override fun getLifecycle(): Lifecycle {
//        return lifecycleRegistry
//    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        lifecycle.addObserver(LifecycleObserver())
//        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }
}