package com.e.buynow.view.fragment.rider.authn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.buynow.R
import com.e.buynow.databinding.FragmentRiderOnboarding2Binding
import com.e.buynow.util.ToastUtil

class RiderOnboarding2Fragment : Fragment() {

    val binding : FragmentRiderOnboarding2Binding by lazy {
        FragmentRiderOnboarding2Binding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setContentView()
        return binding.root
    }

    fun setContentView(){
        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_riderOnboarding2Fragment_to_riderOnboardingFragment)
        }

        binding.continueBtn.setOnClickListener {
            val selectedId = binding.radioGroup.checkedRadioButtonId
            if(selectedId != null) {
                when(selectedId){
                    R.id.registered_driver -> findNavController()
                        .navigate(R.id.action_riderOnboarding2Fragment_to_riderSignInFragment)

                    R.id.freelance_rider -> {//switch to rider registration screen
                     }
                }
            }else{
                ToastUtil.showLong(context, "Please select an option")
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RiderOnboarding2Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}