package com.e.spaceflight.ui.mainactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.spaceflight.R
import com.e.spaceflight.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleAdapter(val listener: ArticleOnClickListener) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    var listArticles = arrayListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentArticle = listArticles[position]
        holder.titleArticle.text = currentArticle.title
        holder.siteArticle.text = currentArticle.newsSite

        Glide.with(holder.imageArticle)
                .load(currentArticle.imageUrl)
                .circleCrop()
                .into(holder.imageArticle)

        holder.itemView.setOnClickListener {
            listener.selectArticle(position)
        }
    }

    override fun getItemCount() = listArticles.size

    interface ArticleOnClickListener {
        fun selectArticle(position: Int)
    }

    fun setData(newList: ArrayList<Article>) {
        this.listArticles = newList
        notifyDataSetChanged()
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val imageArticle: ImageView = item.image_article
        val titleArticle: TextView = item.tv_title_article
        val siteArticle: TextView = item.tv_site_article
    }
}