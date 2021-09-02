package com.e.buynow.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.e.buynow.util.GeneralUtils
import com.e.buynow.view.activity.interfaces.FragmentListener

abstract class BaseFragment: Fragment() {

    private val TAG = "BaseFragment"
    protected var listener: FragmentListener?=null
    protected lateinit var navHostFrag: NavHostFragment
    protected lateinit var navController: NavController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseWidgets()
        Log.e(TAG, "onViewCreated: "+javaClass.simpleName)
    }

    protected  abstract fun initialiseWidgets()

    protected fun showToast(msg:String){
        activity?.let { GeneralUtils.message(it, msg) }
    }

    protected fun findNavHostFragmentFromId(resId:Int){
        navHostFrag= childFragmentManager.findFragmentById(resId) as NavHostFragment
        navController = navHostFrag.navController
    }
}