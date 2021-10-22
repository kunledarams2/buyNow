package com.e.buynow.view.fragment.userprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.buynow.R
import com.e.buynow.databinding.FragmentMOrdersBinding
import com.e.buynow.databinding.FragmentPersonalInfoBinding
import com.e.buynow.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_m_orders.*
import kotlinx.android.synthetic.main.fragment_m_orders.view.*


class MOrders : BaseFragment() {

    private var _binding: FragmentMOrdersBinding? = null
    private val binding get() = _binding!!
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
//        return inflater.inflate(R.layout.fragment_m_orders, container, false)
        _binding = FragmentMOrdersBinding.inflate(inflater,container,false)
        val view = binding.root
        setContentView(view)
        return view
    }

    private fun setContentView(view: View) {
        listener!!.hideBNV()
     view.back_btn.setOnClickListener { findNavController().navigate(R.id.action_MOrders_to_profileHomePage) }

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MOrders().apply {
                arguments = Bundle().apply {

                }
            }
    }
}