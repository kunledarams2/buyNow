package com.e.buynow.view.fragment.authn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.buynow.R
import com.e.buynow.view.fragment.FragmentTitle


class ForgottenPassword : FragmentTitle() {


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
        return inflater.inflate(R.layout.fragment_forgotten_password, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ForgottenPassword().apply {
                arguments = Bundle().apply {

                }
            }
    }
}