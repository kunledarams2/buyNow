package com.e.buynow.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.buynow.R

class ExitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exit)
        finishAndRemoveTask()
    }
}