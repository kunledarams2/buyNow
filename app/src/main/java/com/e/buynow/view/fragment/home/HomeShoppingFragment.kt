package com.e.buynow.view.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.buynow.R
import com.e.buynow.databinding.FragmentHomeShoppingBinding
import com.e.buynow.databinding.FragmentMOrdersBinding
import com.e.buynow.model.CategoriesData
import com.e.buynow.model.ProductData
import com.e.buynow.network.callback.ClickListener
import com.e.buynow.util.AppConstants
import com.e.buynow.util.GeneralUtils
import com.e.buynow.util.ToastUtil
import com.e.buynow.view.adapter.CategoriesAdapter
import com.e.buynow.view.adapter.HomeProductAdapter
import com.e.buynow.view.fragment.BaseFragment
import com.e.buynow.view_model.CategoriesViewModel
import com.e.buynow.view_model.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeShoppingFragment : BaseFragment() {
    private var homeProductAdapter: HomeProductAdapter?=null
    private var productViewModel: ProductViewModel? = null
    private var categoriesViewModel:CategoriesViewModel?=null
    private var categoriesAdapter:CategoriesAdapter?=null

    private var bundle=Bundle()

    private var _binding: FragmentHomeShoppingBinding? = null
    private val binding get() = _binding!!



    override fun initialiseWidgets() {
        homeProductAdapter = HomeProductAdapter()
        categoriesAdapter = CategoriesAdapter()

        with(binding.recyclerView){
            val llm =LinearLayoutManager(context)
            layoutManager = llm
            llm.orientation = LinearLayoutManager.HORIZONTAL
            adapter = homeProductAdapter

        }
        with(binding.listFcategories){
            layoutManager = GridLayoutManager(context, 3)
            adapter=categoriesAdapter
        }

        categoriesAdapter!!.setClickListener(object : ClickListener<CategoriesData>{
            override fun onClick(mode: CategoriesData) {
                bundle.putParcelable("CategoriesData", mode)

            }
        })

        binding.txtCategories.setOnClickListener {
            findNavController().navigate(R.id.action_homeShoppingFragment2_to_categoriesFragment)
        }

        homeProductAdapter!!.setItemClickListener(object :ClickListener<ProductData>{
            override fun onClick(mode: ProductData) {
                bundle.putParcelable("ProductData", mode)
                findNavController().navigate(R.id.action_homeShoppingFragment2_to_itemDetailFragment2, bundle)
            }

        })

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
       /*     homeProductAdapter = HomeProductAdapter()*/
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    /*    val extra = bundleOf("id" to 1)
        findNavController().navigate(R.id.action_homeShoppingFragment2_to_dealsFragment2, extra)*/
        _binding = FragmentHomeShoppingBinding.inflate(inflater,container,false)
        val view = binding.root
//       val view = inflater.inflate(R.layout.fragment_home_shopping, container, false)
        setContentView(view)
        return view
    }

    private fun setContentView(view: View){
  /*      binding.recyclerView.layoutManager =GridLayoutManager(context, 1)
        binding.recyclerView.adapter = homeProductAdapter*/



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        categoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            GeneralUtils.getUserToken(requireContext()).collect{
                token-> if (token.isNotEmpty()){
                productViewModel!!.fetchAProduct(token)
                categoriesViewModel!!.fetchCategories(token)

                }
            }
        }
        popularProductObserver(productViewModel!!)
        catObserver(categoriesViewModel!!)

    }

    private fun popularProductObserver(productViewModel: ProductViewModel){
        productViewModel.getMediatorLiveData().observe(viewLifecycleOwner,  { result->

            if (result.isSuccessful() && !result.getDataList().isNullOrEmpty()){
                Log.d("mProductDatatttt", result.getDataList().toString())
                homeProductAdapter!!.setData(result.getDataList() as ArrayList<ProductData>)
            }
        })
    }

    private fun catObserver(categoriesViewModel: CategoriesViewModel){
        categoriesViewModel.getMediatorLiveData().observe(viewLifecycleOwner, {result->
            if (result.isSuccessful() && !result.getDataList().isNullOrEmpty()){
                categoriesAdapter!!.setData(result.getDataList() as ArrayList<CategoriesData>, "Home")
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HomeShoppingFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}