package com.android.haozi.wanandroid.paging.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.android.haozi.wanandroid.R
import com.android.haozi.wanandroid.bean.ArticleDataBean
import kotlinx.android.synthetic.main.article_list_item_layout.view.*

class ArticleListAdapter(context: Context) : BasePagedListAdapter<ArticleDataBean>(context,
    diffCallback
) {

    companion object{
        val diffCallback = object : DiffUtil.ItemCallback<ArticleDataBean>() {
            override fun areItemsTheSame(oldItem: ArticleDataBean, newItem: ArticleDataBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ArticleDataBean, newItem: ArticleDataBean): Boolean {
                return oldItem.collect == newItem.collect
            }

        }
    }

    override fun getLayoutId(): Int = R.layout.article_list_item_layout

    override fun getViewHolder(view: View): BaseViewHolder<ArticleDataBean> = ArticleListViewHolder(view)

    inner class ArticleListViewHolder(view: View) : BaseViewHolder<ArticleDataBean>(view) {

        override fun setItemViewData(dataBean: ArticleDataBean?) {
            super.setItemViewData(dataBean)
            itemView.article_item_collect.setImageResource(if(dataBean?.collect?:false) R.drawable.ic_article_collect else R.drawable.ic_article_no_collect)
            itemView.article_item_title.text = dataBean?.title
            itemView.article_item_top.visibility = if(dataBean?.type?:-1 == 1) View.VISIBLE else View.GONE
            itemView.article_item_new.visibility = if(dataBean?.fresh?:false) View.VISIBLE else View.GONE
            itemView.article_item_author.text = this@ArticleListAdapter.context.getString(R.string.article_item_author,dataBean?.author)
            itemView.article_item_collect.setOnClickListener {
                dataBean?.collect = if(dataBean?.collect?:false) false else true
                notifyItemChanged(adapterPosition)
            }
        }
    }
}