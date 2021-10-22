package com.e.buynow.view.fragment.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.buynow.R
import com.e.buynow.view.fragment.BaseFragment

class HomeFragment private constructor(): BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun initialiseWidgets() {
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }
}