package com.e.spaceflight.ui.mainactivity

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.spaceflight.AppDatabase
import com.e.spaceflight.model.Article
import com.e.spaceflight.repository.ArticleRepository
import com.e.spaceflight.repository.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val service: Service) : ViewModel() {

    val listArticles = MutableLiveData<ArrayList<Article>>()

    var manageProgressBar = MutableLiveData<String>()

    var showErrorDialog = MutableLiveData<Boolean>()

    fun getAllArticles(_limit: Int, _start: Int) {

        manageProgressBar.value = "start"

        viewModelScope.launch {
            try {
                listArticles.value = service.getArticlesAPI(_limit, _start)
                showErrorDialog.value = false
            } catch (e: Exception) {
                Log.e("MainViewModel", "Erro ao acessar repositorio")
                Log.e("MainViewModel", e.toString())
                showErrorDialog.value = true
            }
            manageProgressBar.value = "finish"
        }
    }

}