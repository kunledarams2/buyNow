package com.e.buynow.view.fragment.userprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.buynow.R
import com.e.buynow.databinding.FragmentSaveAddressBinding


class SaveAddress : Fragment() {

    private var _binding: FragmentSaveAddressBinding? = null
    private val binding get() = _binding!!

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
//        return inflater.inflate(R.layout.fragment_save_address, container, false)
        _binding = FragmentSaveAddressBinding.inflate(inflater,container,false)
        val view = binding.root
        setContentView(view)
        return view
    }

    private fun setContentView(view: View){
        binding.backBtn.setOnClickListener { findNavController().navigate(R.id.action_saveAddress_to_profileHomePage) }
        binding.addNewAddress.setOnClickListener { findNavController().navigate(R.id.action_saveAddress_to_addNewAddress) }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SaveAddress().apply {
                arguments = Bundle().apply {

                }
            }
    }
}