package com.e.buynow.util

import android.app.Activity
import android.app.ActivityOptions
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.util.JsonToken
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.e.buynow.R
import com.e.buynow.model.Data
import com.e.buynow.view.activity.ExitActivity
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object GeneralUtils {


    private var appPref: SharedPreferences? = null
    private var uiHandler: Handler? = null
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = AppConstants.APP_PREFS_NAME)
    val TOKEN = stringPreferencesKey(AppConstants.TOKEN)
    val FIRST_NAME = stringPreferencesKey(AppConstants.FIRST_NAME)
    val LAST_NAME = stringPreferencesKey(AppConstants.LAST_NAME)
    val USER_ID = stringPreferencesKey(AppConstants.USER_ID)
    val USERNAME = stringPreferencesKey(AppConstants.USERNAME)
    val EMAIL = stringPreferencesKey(AppConstants.EMAIL)

    @JvmStatic
    fun message(context: Context, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    @JvmStatic
    fun getAppPref(context: Context): SharedPreferences? {
        if (appPref == null) appPref = context.getSharedPreferences(
            AppConstants.APP_PREFS_NAME,
            Context.MODE_PRIVATE
        )
        return appPref
    }

    @JvmStatic
    suspend fun saveToken(context: Context, token: String) {
        context.dataStore.edit { settings ->
            settings[TOKEN] = token
        }
    }

    @JvmStatic
    fun getUserToken( context: Context):Flow<String> {
        return context.dataStore.data.map { 
            "Bearer ${it[TOKEN].toString()}"
        }
    }

    suspend fun setUserDetails(context: Context, data: Data){
        context.dataStore.edit { store->
            store[FIRST_NAME] = data.firstName
            store[LAST_NAME] = data.lastName
            store[EMAIL] = data.email
            store[USERNAME]= data.username
            store[USER_ID] = data._id
            store[TOKEN] = data.token
        }
    }


    fun getUserDetails( context: Context):Flow<Data> = context.dataStore.data.map { preference-> Data(
            preference[TOKEN] ?: "",
            preference[EMAIL] ?: "",
            preference[USER_ID] ?: "",
            preference[USERNAME] ?: "",
            preference[LAST_NAME] ?: "",
            preference[FIRST_NAME] ?: "",

            )
    }

    fun getData(context: Context, key:String): Flow<String> = context.dataStore.data.map {
        it[stringPreferencesKey(key)]!!
    }



    @JvmStatic
    fun getHandler(): Handler? {
        if (uiHandler== null){
            uiHandler = Handler(Looper.getMainLooper())
        }
        return uiHandler
    }


    @JvmStatic
    fun showBalloon(view: View, context: Context, msg:String, resColor:Int = R.color.black){

        val balloon = createBalloon(context) {
            setArrowSize(10)
            setWidthRatio(0.5f)
            setHeight(65)
            setArrowPosition(0.7f)
            setCornerRadius(10f)
            setAlpha(0.9f)
            setText(msg)
            setTextColorResource(R.color.white)
            setBackgroundColorResource(resColor)
            setBalloonAnimation(BalloonAnimation.FADE)
            setLifecycleOwner(lifecycleOwner)
        }

        balloon.show(view)
        balloon.dismissWithDelay(1200)
    }

    @JvmStatic
    fun loadImg(img: ImageView, url: String?){
        if (url!=null && !url.isBlank())
            Picasso.get().load(url).into(img)
    }

    @JvmStatic
    fun exitApp(activity: Activity) {
        activity.finishAndRemoveTask()
        val intent = Intent(activity, ExitActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra("EXIT", true)
        activity.startActivity(intent)
    }

    @JvmStatic
    fun transitionActivity(oldActivity: Activity, newActivity: Class<*>?) {
        oldActivity.finishAndRemoveTask()
        oldActivity.startActivity(Intent(oldActivity, newActivity),
            ActivityOptions.makeSceneTransitionAnimation(oldActivity).toBundle())
        //Animatoo.animateFade(oldActivity)
    }

    @JvmStatic
    fun transitionActivity(oldActivity: Activity, intent: Intent?) {
        oldActivity.finishAndRemoveTask()
        oldActivity.startActivity(intent,
            ActivityOptions.makeSceneTransitionAnimation(oldActivity).toBundle())
        //Animatoo.animateFade(oldActivity)
    }

    @JvmStatic
    fun showAlertMessage(activity: Activity, title: String?, message: String?) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(
            activity,
            android.R.style.Theme_DeviceDefault_Dialog_Alert
        )

        alertDialog.setCancelable(true)
            .setIcon(R.mipmap.ic_launcher).setMessage(message)
            .setTitle(title).setNeutralButton("Ok"
                ) { dialog, i -> dialog.dismiss() }
        getHandler()?.post {
            try {
                alertDialog.show()
            } catch (e: WindowManager.BadTokenException) {
                e.printStackTrace()
                Log.w(activity.javaClass.simpleName, e.fillInStackTrace())
            }
        }
    }

    fun resizeBitmap(source: Bitmap, maxLength: Int): Bitmap? {
        return try {
            if (source.height >= source.width) {
                if (source.height <= maxLength) { // if image already smaller than the required height
                    return source
                }
                val aspectRatio = source.width.toDouble() / source.height.toDouble()
                val targetWidth = (maxLength * aspectRatio).toInt()
                Bitmap.createScaledBitmap(source, targetWidth, maxLength, false)
            } else {
                if (source.width <= maxLength) { // if image already smaller than the required height
                    return source
                }
                val aspectRatio = source.height.toDouble() / source.width.toDouble()
                val targetHeight = (maxLength * aspectRatio).toInt()
                Bitmap.createScaledBitmap(source, maxLength, targetHeight, false)
            }
        } catch (e: Exception) {
            source
        }
    }
}