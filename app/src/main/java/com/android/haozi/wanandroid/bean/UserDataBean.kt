package com.android.haozi.wanandroid.bean

data class UserDataBean (var admin: Boolean = false,
                         var chapterTops: List<*>? = null,
                         var collectIds: List<Int>? = null,
                         var email: String? = null,
                         var icon: String? = null,
                         var id: Int = 0,
                         var nickname: String? = null,
                         var password: String? = null,
                         var token: String? = null,
                         var type: Int = 0,
                         var username: String? = null)