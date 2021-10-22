package com.e.buynow.view.fragment.userprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.buynow.R
import com.e.buynow.view.fragment.BaseFragment


class ResetPassword : BaseFragment() {
    override fun initialiseWidgets() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reset_password, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ResetPassword().apply {
                arguments = Bundle().apply {

                }
            }
    }
}