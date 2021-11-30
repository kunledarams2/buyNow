package com.e.buynow.view.fragment.authn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.buynow.R
import com.e.buynow.databinding.FragmentForgottenPasswordBinding
import com.e.buynow.view.fragment.FragmentTitle


class ForgottenPassword : FragmentTitle() {

    val binding: FragmentForgottenPasswordBinding by lazy {
        FragmentForgottenPasswordBinding.inflate(layoutInflater)
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

        setContentView()
        return binding.root
    }

    fun setContentView() {
        binding.createAccount.setOnClickListener { findNavController().navigate(R.id.action_forgottenPassword_to_signUp) }
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