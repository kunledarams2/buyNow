package com.e.buynow.view.fragment.rider.authn

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.buynow.R
import com.e.buynow.databinding.FragmentLoginSuccessBinding
import com.e.buynow.view.activity.RiderMainActivity

class LoginSuccessFragment : Fragment() {

    val binding: FragmentLoginSuccessBinding by lazy {
        FragmentLoginSuccessBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.continueBtn.setOnClickListener {
            startActivity(Intent(requireActivity(), RiderMainActivity::class.java))
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LoginSuccessFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}