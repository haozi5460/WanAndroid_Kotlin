package com.android.haozi.wanandroid.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userDataBase")
class User {
    @PrimaryKey(autoGenerate = true)    // 单个主键设置为自增长
    var id = 0
    @ColumnInfo(name = "nameUser")  // 定义列名
    var name: String? = null
}