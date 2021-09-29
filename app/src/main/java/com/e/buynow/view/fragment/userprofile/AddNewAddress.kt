package com.e.buynow.view.fragment.userprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.buynow.R
import com.e.buynow.databinding.FragmentAddNewAddressBinding
import com.e.buynow.databinding.FragmentSaveAddressBinding
import com.e.buynow.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.content_home.*


class AddNewAddress : BaseFragment() {


    private var _binding: FragmentAddNewAddressBinding? = null
    private val binding get() = _binding!!
    override fun initialiseWidgets() {
        TODO("Not yet implemented")

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
//        return inflater.inflate(R.layout.fragment_add_new_address, container, false)
        _binding = FragmentAddNewAddressBinding.inflate(inflater,container,false)
        val view = binding.root
        setContentView()
        return view
    }
    private fun setContentView(){

        binding.createBtn.setOnClickListener {  }
        binding.backBtn.setOnClickListener { findNavController().navigate(R.id.action_addNewAddress_to_saveAddress) }
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            AddNewAddress().apply {
                arguments = Bundle().apply {

                }
            }
    }
}