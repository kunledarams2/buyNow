package com.e.buynow.view.fragment.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.buynow.R
import com.e.buynow.databinding.FragmentItemDetailBinding
import com.e.buynow.view.fragment.BaseFragment

class ItemDetailsFragment:BaseFragment() {

    private lateinit var binding: FragmentItemDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }


    override fun initialiseWidgets() {
        TODO("Not yet implemented")
    }
}