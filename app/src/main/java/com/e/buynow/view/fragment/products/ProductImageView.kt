package com.e.buynow.view.fragment.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.buynow.R
import com.e.buynow.databinding.FragmentItemDetailBinding
import com.e.buynow.databinding.FragmentItemDetailsBinding
import com.e.buynow.databinding.FragmentProductImageViewBinding
import com.e.buynow.model.ProductData
import com.e.buynow.view.adapter.ImageSliderAdapter
import com.e.buynow.view.fragment.BaseFragment


class ProductImageView : BaseFragment() {

    lateinit var binding:FragmentProductImageViewBinding
    private var imageSliderAdapter: ImageSliderAdapter? = null
    private var productData: ProductData? = null
    private val bundle = Bundle()


    override fun initialiseWidgets() {
        listener!!.hideBNV()

        productData = requireArguments().getParcelable("ProductData")!!
        imageSliderAdapter = ImageSliderAdapter()
        imageSliderAdapter!!.setImageData(productData!!.images as ArrayList<String>)
        binding.imageViewpager.adapter = imageSliderAdapter
        binding.circlePageIndicator.setViewPager(binding.imageViewpager)

        binding.navBack.setOnClickListener {
            bundle.putParcelable("ProductData", productData)
            findNavController().navigate(R.id.action_productImageView_to_itemDetailFragment2, bundle ) }
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
        binding = FragmentProductImageViewBinding.inflate(inflater, container, false)

        return  binding.root /*inflater.inflate(R.layout.fragment_item_details, container, false)*/
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProductImageView().apply {
                arguments = Bundle().apply {

                }
            }
    }
}