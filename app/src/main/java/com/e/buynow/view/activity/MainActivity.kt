package com.e.buynow.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.buynow.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {

    }
}