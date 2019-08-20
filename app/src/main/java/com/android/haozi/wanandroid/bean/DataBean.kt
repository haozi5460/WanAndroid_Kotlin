package com.android.haozi.wanandroid.bean

data class DataBean (var admin: Boolean = false,
                        var chapterTops: List<*>? = null,
                        var collectIds: List<Int>? = null,
                        var email: String? = null,
                        var icon: String? = null,
                        var id: Int = 0,
                        var nickname: String? = null,
                        var password: String? = null,
                        var token: Long = 0,
                        var username: String? = null)