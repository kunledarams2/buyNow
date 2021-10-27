package com.e.buynow.view.fragment.products

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.e.buynow.R
import com.e.buynow.databinding.FragmentItemDetailBinding
import com.e.buynow.model.Cart
import com.e.buynow.model.ProductData
import com.e.buynow.network.callback.ClickListener
import com.e.buynow.util.ToastUtil
import com.e.buynow.view.adapter.ImageSliderAdapter
import com.e.buynow.view.fragment.BaseFragment
import com.e.buynow.view_model.CartViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_dialog_cont_shopping.view.*
import kotlinx.coroutines.launch


class ItemDetailFragment : BaseFragment() {

    private var productData: ProductData? = null
    private lateinit var binding: FragmentItemDetailBinding
    private var imageSliderAdapter: ImageSliderAdapter? = null
    private var cartViewModel:CartViewModel?=null
    private val bundle = Bundle()

    @SuppressLint("SetTextI18n")
    override fun initialiseWidgets() {
        listener!!.showBNV()

        productData = requireArguments().getParcelable("ProductData")!!
        Log.d("mProductData", productData.toString())

        with(productData!!) {
            binding.txtProdName.text = name
            binding.txtProdDesc.text = description
            binding.buyButton.text = "Buy ₦$price"

        }
        binding.navBack.setOnClickListener { findNavController().navigate(R.id.action_itemDetailFragment2_to_homeShoppingFragment2) }

        imageSliderAdapter = ImageSliderAdapter()
        imageSliderAdapter!!.setImageData(productData!!.images as ArrayList<String>)
        binding.imageViewpager.adapter = imageSliderAdapter
        binding.circlePageIndicator.setViewPager(binding.imageViewpager)

        imageSliderAdapter!!.setOnItemClickListener(object : ClickListener<String> {
            override fun onClick(mode: String) {
                bundle.putParcelable("ProductData", productData)
                findNavController().navigate(R.id.action_itemDetailFragment2_to_productImageView, bundle
                )
            }

        })

        binding.buyButton.setOnClickListener { insertItemsOnCart() }

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
        binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]

    }

    companion object {

        @JvmStatic
        fun newInstance() =
                ItemDetailFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

   private  fun insertItemsOnCart() {

        val cart = Cart(null,
                productData!!.name,
                productData!!.price.toInt(),
                productData!!._id,
                1,
                productData!!.images[0])

       viewLifecycleOwner.lifecycleScope.launch {
           cartViewModel!!.insertItem(cart)
       }
       cartViewModel!!.response.observe(this){

           if (it >0){
               openBottomDialog()
           }
       }

    }


    private fun openBottomDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val view = LayoutInflater.from(context).inflate(R.layout.bottom_dialog_cont_shopping, null)

        view.continue_btn.setOnClickListener {
            bottomSheetDialog.dismiss()
            findNavController().navigate(R.id.action_itemDetailFragment2_to_homeShoppingFragment2)

        }
        view.check_out_btn.setOnClickListener {
            bottomSheetDialog.dismiss()
             }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.dismissWithAnimation = true
        bottomSheetDialog.setCanceledOnTouchOutside(false)
        bottomSheetDialog.show()
    }
}