package com.android.haozi.wanandroid.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.haozi.wanandroid.WanAndroidApplication

@Database(entities = arrayOf(User::class), version = 1)
abstract class TestDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile private var INSTANCE: TestDatabase? = null

        fun getInstance(context: Context): TestDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                TestDatabase::class.java, "TestData.db")
                .build()
    }
}