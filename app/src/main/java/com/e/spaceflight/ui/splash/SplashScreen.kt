package com.e.spaceflight.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.e.spaceflight.R
import com.e.spaceflight.ui.mainactivity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {

    val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        callMainActivity()
    }

    private fun callMainActivity() {
        scope.launch {
            delay(2000)
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)

        }
    }
}