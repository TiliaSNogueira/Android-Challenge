package com.e.spaceflight.repository

import com.e.spaceflight.ArticleDao
import com.e.spaceflight.model.Article

class ArticleRepository(private val articleDao: ArticleDao) {

    val readAllArticles: List<Article> = articleDao.getAllArticles()

    suspend fun saveFavorite(article: Article) {
        articleDao.saveArticle(article)
    }

    suspend fun deleteFavorite(article: Article){
        articleDao.deleteArticle(article)
    }

}