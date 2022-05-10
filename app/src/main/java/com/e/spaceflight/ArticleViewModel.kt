package com.e.spaceflight

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.e.spaceflight.model.Article
import com.e.spaceflight.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData: List<Article>
    private val repository: ArticleRepository

    init {
        val articleDao = AppDatabase.getDatabase(application).articleDao()
        repository = ArticleRepository(articleDao)
        readAllData = repository.readAllArticles
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveFavorite(article)
        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(article)
        }
    }


}