package com.e.spaceflight.ui.mainactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.spaceflight.R
import com.e.spaceflight.repository.service
import com.e.spaceflight.ui.DialogActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog_error.view.*


class MainActivity : AppCompatActivity(), ArticleAdapter.ArticleOnClickListener
//,    ItemDialogFragment.DialogListener
{

    private lateinit var adap: ArticleAdapter
    private lateinit var llmanager: LinearLayoutManager
    private val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(service) as T
            }
        }
    }

    //lateinit var tituloDaqui: String


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


        viewModel.getAllArticles()
        viewModel.listArticles.observe(this, {
            adap.setData(it)
        })

        viewModel.showErrorDialog.observe(this, {
            if (it == true) {
                showErrorDialog()
                Log.i("ENTROU NO IF TRUE", it.toString())
            }
        })


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
        Toast.makeText(this, "CLICOU NO ITEM", Toast.LENGTH_SHORT).show()

        val article = adap.listArticles[position]


        val intent = Intent(this, DialogActivity::class.java)
        intent.putExtra("key", article)
        startActivity(intent)


        // callDialogFragment()
    }

//    override fun onDialogPositiveClick(dialog: DialogFragment) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onDialogNegativeClick(dialog: DialogFragment) {
//        TODO("Not yet implemented")
//    }
//
//
//
//    fun callDialogFragment() {
//        val newFragment = ItemDialogFragment()
//        newFragment.show(supportFragmentManager, "missiles")
//
//    }

}


