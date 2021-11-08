package com.e.buynow.view.fragment.rider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.e.buynow.R

class RiderProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rider_profile, container, false)
        view.findViewById<ImageView>(R.id.rider_profile_img).clipToOutline = true
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RiderProfileFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}