package com.android.haozi.wanandroid.workmanager

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

class TestWorkManagerActivity : AppCompatActivity() {
    lateinit var workRequest: WorkRequest

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        var myConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(false)
            .setRequiresCharging(true)
            .setRequiresDeviceIdle(true)
            .setRequiresStorageNotLow(true)
            .build()

        workRequest = OneTimeWorkRequest.Builder(TestWorker::class.java).build()
        var workRequest1 = PeriodicWorkRequest.Builder(TestWorker::class.java,60, TimeUnit.SECONDS).build()
        WorkManager.getInstance(this).enqueue(workRequest)

        observeWorkStatus()
    }

    private fun observeWorkStatus() {
        WorkManager
            .getInstance(this)
            .getWorkInfoByIdLiveData(workRequest.id)
            .observe(this, Observer {
                if(it.state.isFinished){
                    Log.e("WorkManager","Worker finished")
                }
            })
    }
}