package com.e.buynow.view.fragment.rider.authn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.buynow.R
import com.e.buynow.databinding.FragmentRiderOnboardingBinding

class RiderOnboardingFragment : Fragment() {

    val binding : FragmentRiderOnboardingBinding by lazy {
        FragmentRiderOnboardingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setContentView()
        return binding.root
    }

    fun setContentView(){
        binding.getStartedBtn.setOnClickListener {
            findNavController().navigate(R.id.action_riderOnboardingFragment_to_riderOnboarding2Fragment)
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            RiderOnboardingFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}