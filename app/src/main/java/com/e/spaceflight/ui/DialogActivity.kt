package com.e.spaceflight.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.e.spaceflight.R
//activity pequena, se foir usar ela mesmo, fazer o bundle
class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme_Dialog); // can either use R.style.AppTheme_Dialog or R.style.AppTheme as deined in styles.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog)

        val resposta = intent.extras
        Toast.makeText(this, resposta.toString(), Toast.LENGTH_SHORT).show()
    }
}