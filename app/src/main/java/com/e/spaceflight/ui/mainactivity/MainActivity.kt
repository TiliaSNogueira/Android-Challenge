package com.e.spaceflight.ui.mainactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.spaceflight.ItemDialogFragment
import com.e.spaceflight.R
import com.e.spaceflight.repository.service
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog_error.view.*


class MainActivity : AppCompatActivity(), ArticleAdapter.ArticleOnClickListener
   // ,ItemDialogFragment.DialogListener
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

        val article = adap.listArticles[position]


//        Dessa forma, chamamos uma activity com forma de dialog
//        val intent = Intent(this, DialogActivity::class.java)
//        intent.putExtra("key", article)
//        startActivity(intent)

        //Dessa forma chamamos um Dialog Fragment
        val dialogFrag = ItemDialogFragment()
        val bundleee = Bundle()
        bundleee.putSerializable("key", article)
        dialogFrag.arguments = bundleee
        dialogFrag.show(supportFragmentManager, "dialog")


    }

//    override fun onDialogReadClick(dialog: DialogFragment) {
//        Log.i("MAINNNNN", "onDialogReadClick: clicou")
////        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articleDetails.url))
////        startActivity(browserIntent)
//    }
//
//    override fun onDialogReturnClick(dialog: DialogFragment) {
//        Log.i("MAINNNNN", "onDialogReturnClick: clicou")
//        //dialog.dismiss()
//    }


}


