package com.e.buynow.view.fragment.rider.authn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.buynow.R
import com.e.buynow.databinding.FragmentRiderSignInBinding
import com.e.buynow.network.callback.EndPoint
import javax.inject.Inject

class RiderSignInFragment : Fragment(){
    private val binding : FragmentRiderSignInBinding by lazy {
        FragmentRiderSignInBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var endPoint: EndPoint

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setContentView()
        return binding.root
    }

    private fun setContentView(){
        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_riderSignInFragment_to_riderOnboarding2Fragment2)
        }
        binding.login.setOnClickListener { loginRider() }
    }

    private fun loginRider(){

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RiderSignInFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}