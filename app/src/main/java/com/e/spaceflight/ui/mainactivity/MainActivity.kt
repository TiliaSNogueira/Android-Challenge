package com.e.spaceflight.ui.mainactivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.spaceflight.ItemDialogFragment
import com.e.spaceflight.R
import com.e.spaceflight.model.Article
import com.e.spaceflight.repository.service
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog_error.view.*
import javax.security.auth.login.LoginException
import kotlin.math.log


class MainActivity : AppCompatActivity(), ArticleAdapter.ArticleOnClickListener, SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {

    var listRefresh: ArrayList<Article> = arrayListOf()

    //quantidade de articles retornados (15)
    var _limit = 15

    //pula uma quantidade de articles quando faz requisição
    var _start = 0
    private lateinit var adap: ArticleAdapter
    private lateinit var llmanager: LinearLayoutManager

    var pagScrol = 0

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

        viewModel.getAllArticles(_start)

        viewModel.listArticles.observe(this, {
            //listRefresh = it
            listRefresh.addAll(it)
            adap.setData(listRefresh)
        })

        viewModel.showErrorDialog.observe(this, {
            if (it == true) {
                showErrorDialog()
            }
        })

        setScrollView(recycler)

        val searchView = search_main
        searchView.setOnQueryTextListener(this)

    }

    private fun setScrollView(view: View) {
        recycler_main?.run {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val adapItens = adap?.itemCount
                    val lastItenVisible = llmanager.findLastVisibleItemPosition()

                    if (pagScrol == 0) {
                        Log.i("ENTROU NO IF DO PAG", pagScrol.toString())
                        if (lastItenVisible == adapItens - 1) {
                            _start += 15
                            Log.i("ENTROU IF DO SCROLL", _start.toString())
                            viewModel.getAllArticles(_start
                                    //, _limit
                            )

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
        val customDialog = LayoutInflater.from(this).inflate(R.layout.custom_dialog_error, null)
        customDialog.btn_error_dialog.setOnClickListener {
            this.finishAffinity()
        }

        val builder = AlertDialog.Builder(this)
        builder.setView(customDialog)
        builder.create().show()
    }

    override fun selectArticle(position: Int) {

        val article = adap.listArticles[position]


//        Dessa forma, chamamos uma activity com forma de dialog
//        val intent = Intent(this, DialogActivity::class.java)
//        intent.putExtra("key", article)
//        startActivity(intent)

        //Dessa forma chamamos um Dialog Fragment
        val dialogFrag = ItemDialogFragment()
        val bundle = Bundle()
        bundle.putSerializable("key", article)
        dialogFrag.arguments = bundle
        dialogFrag.show(supportFragmentManager, "dialog")


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        pagScrol = 1

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        pagScrol = 1

        Log.i("ONQUERY listRefresh", listRefresh.toString())

        val filterList = listRefresh.filter { it.title.contains(newText.toString(), ignoreCase = true) }
        Log.i("ONQUERY filterList", filterList.toString())

        adap.setData(filterList as ArrayList<Article>)
        Log.i("FILTRO p/ ADAPTER ", filterList.toString())


        //se faz algo como clear nem aparece primeiro

        adap.listArticles = filterList
        adap.notifyDataSetChanged()
        adap.setData(adap.listArticles)
        Log.i("mandando fil p/ adap", adap.listArticles.toString())

        return true
    }

}


