package com.android.haozi.wanandroid.room

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class TestDataBaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        var user = User()
        user.id = 0
        user.name = "James"

        TestDatabase.getInstance(this).userDao().insertUser(user)
    }
}