package com.e.spaceflight.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.spaceflight.R
import com.e.spaceflight.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dialog.*

//activity pequena, se foir usar ela mesmo, fazer o bundle
class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme_Dialog); // can either use R.style.AppTheme_Dialog or R.style.AppTheme as deined in styles.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog)


        val articleDetails = intent.getSerializableExtra("key") as Article

        val picasso = Picasso.get()
        title_dialog.setText(articleDetails.title)
        summary_dialog_item.setText(articleDetails.summary)
        picasso.load(articleDetails.imageUrl).into(image_dialog)

        btn_read_dialog.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articleDetails.url))
            startActivity(browserIntent)
        }

        btn_return_dialog.setOnClickListener {
            finish()
        }






    }
}