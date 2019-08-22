package com.android.haozi.wanandroid.utils

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferenceUtil<T>(val key: String, val default: T): ReadWriteProperty<Any?, T> {

    companion object{
        lateinit var preference: SharedPreferences
        const val PreferenceName = "WanAndroid_SharePreference"
        fun init(context: Context){
            preference = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE)
        }
    }


    override fun getValue(thisRef: Any?, property: KProperty<*>): T = getPreferenceValue()

    private fun getPreferenceValue(): T {
        return with(preference){
            var value: Any = when(default){
                is Int -> getInt(key,default)
                is Boolean -> getBoolean(key,default)
                is String -> getString(key,default)
                is Float -> getFloat(key, default)
                is Long -> getLong(key, default)
                else -> throw IllegalAccessException("This type can be saved into Preferences")
            }
            value as T
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = setPreferenceValue(value)

    private fun setPreferenceValue(value: T) {
        with(preference.edit()){
            when(value){
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                else -> throw IllegalAccessException("This type can not be saved into Preferences")
            }
        }.commit()
    }

}