package com.e.spaceflight.ui.mainactivity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.spaceflight.R
import com.e.spaceflight.model.Article
import com.e.spaceflight.repository.service
import com.e.spaceflight.ui.dialog.DialogFragmentError
import com.e.spaceflight.ui.dialog.DialogFragmentItemArticle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ArticleAdapter.ArticleOnClickListener, SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {

    var listArticles: ArrayList<Article> = arrayListOf()

    //parametros para a requisição à API
    var _limit = 15
    var _start = 0

    private lateinit var adap: ArticleAdapter
    private lateinit var llmanager: LinearLayoutManager

    //flag para controlar a paginação
    //quando está filtrando, o tamanho da lista do adapter diminui, então fazia nova requisição automaticamente e aparecia logo abaixo dos itens filtrados
    //com isso, podemos controlar se é pra fazer nova requisição ou não
    var flagPag = 0

    private val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(service) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setProgressBar()

        llmanager = LinearLayoutManager(this)
        adap = ArticleAdapter(this)

        val recycler = recycler_main
        recycler.layoutManager = llmanager
        recycler.setHasFixedSize(true)
        recycler.adapter = adap

        viewModel.getAllArticles(_limit, _start)

        viewModel.listArticles.observe(this, {
            listArticles.addAll(it)
            adap.setData(listArticles)
        })

        viewModel.showErrorDialog.observe(this, {
            if (it == true) {
                showErrorDialog()
            }
        })

        //função que vai fazer nova requisição conforme os items forem sendo deslizados pela tela
        setScrollView(recycler)

        //configurando a pesquisa
        callSearch()

    }


    private fun callSearch() {
        val searchView = search_main
        searchView.setOnQueryTextListener(this)
    }

    private fun setScrollView(view: View) {
        recycler_main?.run {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val adapItens = adap.itemCount
                    val lastItenVisible = llmanager.findLastVisibleItemPosition()

                    if (flagPag == 0) {
                        if (lastItenVisible == adapItens - 1) {
                            _start += 15
                            viewModel.getAllArticles(_limit, _start)
                        }
                    }
                }
            })
        }
    }

    private fun setProgressBar() {
        viewModel.manageProgressBar.observe(this, {
            if (it == "start") {
                progressBar_main.visibility = View.VISIBLE
            } else {
                progressBar_main.visibility = View.INVISIBLE
            }
        })
    }

    private fun showErrorDialog() {
        val errorDialog = DialogFragmentError()
        errorDialog.show(supportFragmentManager, "error")
    }

    override fun selectArticle(position: Int) {
        val article = adap.listArticles[position]

        //Chama o Dialog Fragment e passa o article clicado para ele poder exibir os dados
        val dialogFrag = DialogFragmentItemArticle()
        val bundle = Bundle()
        bundle.putSerializable("key", article)
        dialogFrag.arguments = bundle
        dialogFrag.show(supportFragmentManager, "dialog")
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        flagPag = 1
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        flagPag = 1

        //pega a lista que recebeu como resposta da requisição, filtra e passa pro adapter
        val filterList = listArticles.filter { it.title.contains(newText.toString(), ignoreCase = true) }
        adap.setData(filterList as ArrayList<Article>)
        adap.listArticles = filterList
        adap.setData(adap.listArticles)

        return true
    }
}


