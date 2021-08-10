package com.e.buynow.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.buynow.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        try {
            Thread.sleep(1000)
            tryNext()
        } catch (e: InterruptedException) {
        }
    }

    private fun tryNext() {
        startActivity(Intent(this, OnBoardingActivity::class.java))
    }
}