package com.android.haozi.wanandroid.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


class TestWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        doSomeJob()

        return Result.success()
    }

    private fun doSomeJob() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}