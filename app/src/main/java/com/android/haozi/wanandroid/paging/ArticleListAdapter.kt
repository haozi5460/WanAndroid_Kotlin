package com.android.haozi.wanandroid.paging

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.android.haozi.wanandroid.bean.ArticleDataBean

class ArticleListAdapter(context: Context) : BasePagedListAdapter<ArticleDataBean>(context, diffCallback) {

    companion object{
        val diffCallback = object : DiffUtil.ItemCallback<ArticleDataBean>() {
            override fun areItemsTheSame(oldItem: ArticleDataBean, newItem: ArticleDataBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ArticleDataBean, newItem: ArticleDataBean): Boolean {
                return oldItem.hasHearted == newItem.hasHearted
            }

        }
    }

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}