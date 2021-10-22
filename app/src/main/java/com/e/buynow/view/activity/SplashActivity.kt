package com.e.buynow.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.e.buynow.R
import com.e.buynow.util.GeneralUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            withContext(Dispatchers.Main){
                tryNext()
            }

        }

       /* try {
            Thread.sleep(1000)


        } catch (e: InterruptedException) {
        }*/
    }





    private suspend fun tryNext() {

        GeneralUtils.getUserToken(this).collect {
            Log.d("SplashScreen", it)
            if (it.isNotEmpty() && !it.contains("null")){
                startActivity(Intent(this, MainActivity::class.java))
            } else   startActivity(Intent(this, OnBoardingActivity::class.java))
        }

    }
}