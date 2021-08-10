package com.e.buynow.util


import android.content.Context
import android.util.Log
import android.widget.Toast


object ToastUtil {

    @JvmStatic
    fun showLong(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    fun showShort(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun log(Tag:String, mgs:String){
        Log.d(Tag, "---_-_---__-_----_--_-  $mgs")
    }
}