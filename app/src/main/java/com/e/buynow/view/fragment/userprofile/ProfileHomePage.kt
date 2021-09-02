package com.e.buynow.view.fragment.userprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.buynow.R
import kotlinx.android.synthetic.main.fragment_profile_home_page.view.*


class ProfileHomePage : Fragment(), View.OnClickListener {


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
       val view = inflater.inflate(R.layout.fragment_profile_home_page, container, false)
        setContentView(view)
        return view
    }

    private fun setContentView(view: View){
        view.personal_Info.setOnClickListener(this)
        view.m_order.setOnClickListener(this)
        view.saved_address.setOnClickListener(this)
        view.password_reset.setOnClickListener(this)
        view.logout.setOnClickListener(this)
    }



    companion object {

        @JvmStatic
        fun newInstance() =
            ProfileHomePage().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClick(p0: View?) {

        when(p0!!.id){
            R.id.personal_Info->  findNavController().navigate(R.id.action_profileHomePage_to_personalInfo)
            R.id.m_order->  findNavController().navigate(R.id.action_profileHomePage_to_MOrders)
            R.id.saved_address->  findNavController().navigate(R.id.action_profileHomePage_to_saveAddress)
            R.id.password_reset->  findNavController().navigate(R.id.action_profileHomePage_to_resetPassword)
        }
    }
}