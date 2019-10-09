package com.android.haozi.wanandroid.utils

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import com.android.haozi.wanandroid.R
import java.text.SimpleDateFormat
import java.util.*

object Util {
    var SECOND = 1000
    var MINUTE = 60 * SECOND
    var ONE_HOUR = 60 * MINUTE
    var ONE_DAY = 24 * ONE_HOUR

    fun getArticlePublishTime(context: Context, publishTime: Long) : String? {
        var nowTime = System.currentTimeMillis()
        var intervalTime = nowTime - publishTime

        when(intervalTime){
            in 0..ONE_HOUR -> {
                return getMinuteTimeString(context,intervalTime)
            }

            in ONE_HOUR..ONE_DAY -> {
                return getHourTimeString(context,intervalTime)
            }

            in ONE_DAY..3* ONE_DAY -> {
                return getDayTimeString(context,intervalTime)
            }

            else -> {
                return getDateTimeString(publishTime)
            }
        }
    }

    private fun getMinuteTimeString(context: Context, intervalTime: Long): String? {
        var minute = (intervalTime/ MINUTE).toString()
        return context.getString(R.string.publish_time_minute,minute)
    }

    private fun getHourTimeString(context: Context, intervalTime: Long): String? {
        var hour = (intervalTime/ ONE_HOUR).toString()
        return context.getString(R.string.publish_time_hour,hour)
    }

    private fun getDayTimeString(context: Context, intervalTime: Long): String? {
        var day = (intervalTime/ ONE_DAY).toString()
        return context.getString(R.string.publish_time_day,day)
    }

    private fun getDateTimeString(intervalTime: Long): String? {
        var publishTimeString: String? = null
        try {
            var dateFormat = SimpleDateFormat("yyyy-MM-dd")
            var date = Date(intervalTime)
            publishTimeString = dateFormat.format(date)
        }catch (ep: Exception){
            Log.e("",ep.toString())
        }
        return publishTimeString
    }
}