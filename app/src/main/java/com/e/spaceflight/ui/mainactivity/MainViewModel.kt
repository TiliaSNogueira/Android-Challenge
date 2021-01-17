package com.e.spaceflight.ui.mainactivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.spaceflight.model.Article
import com.e.spaceflight.repository.Service
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(val service: Service) : ViewModel() {

    val listArticles = MutableLiveData<List<Article>>()

    var manageProgressBar = MutableLiveData<String>()

    var showErrorDialog = MutableLiveData<Boolean>()



    fun getAllArticles() {

        manageProgressBar.value = "start"


        viewModelScope.launch {
            try {
                listArticles.value = service.getArticlesAPI()
                showErrorDialog.value = false
            } catch (e: Exception) {
                Log.e("MainViewModel", "Erro ao acessar repositorio")
                showErrorDialog.value = true
            }
            manageProgressBar.value = "finish"
        }

    }


}