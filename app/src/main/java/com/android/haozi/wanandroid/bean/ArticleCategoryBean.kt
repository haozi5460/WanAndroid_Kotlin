package com.android.haozi.wanandroid.bean

import com.android.haozi.wanandroid.view.extendview.BaseExtendBean

class ArticleCategoryBean(var children: List<ArticleCategoryBean>?,
                          var courseId: Int = -1,
                          var id: Int = -1,
                          var order: Int = 0,
                          var parentChapterId: Int = -1,
                          var  userControlSetTop: Boolean = false,
                          var visible: Int = -1,
                          name: String?,
                          hasChoosed: Boolean = false
) : BaseExtendBean(name,hasChoosed)