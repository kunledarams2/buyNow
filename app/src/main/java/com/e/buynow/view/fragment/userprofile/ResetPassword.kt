package com.e.buynow.view.fragment.userprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.buynow.R
import com.e.buynow.databinding.FragmentResetPasswordBinding
import com.e.buynow.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_reset_password.view.*


class ResetPassword : BaseFragment() {

    val binding: FragmentResetPasswordBinding by lazy{
        FragmentResetPasswordBinding.inflate(layoutInflater)
    }

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

        setContentView()
        return binding.root
    }

    fun setContentView(){
        listener!!.hideBNV()
        binding.imageButton.setOnClickListener{
            findNavController().navigate(R.id.action_resetPassword_to_profileHomePage)
        }
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