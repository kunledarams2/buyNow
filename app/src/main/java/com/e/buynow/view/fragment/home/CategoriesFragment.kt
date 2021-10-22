package com.e.buynow.view.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.buynow.databinding.FragmentCategoriesBinding
import com.e.buynow.model.CategoriesData
import com.e.buynow.util.GeneralUtils
import com.e.buynow.view.adapter.CategoriesAdapter
import com.e.buynow.view.fragment.BaseFragment
import com.e.buynow.view_model.CategoriesViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CategoriesFragment : BaseFragment() {

    lateinit var binding: FragmentCategoriesBinding
    lateinit var categoriesData: CategoriesData
    lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var categoriesViewModel: CategoriesViewModel

    override fun initialiseWidgets() {
        listener!!.showBNV()

        /* categoriesData = requireArguments().getParcelable("CategoriesData")!!
         Log.d("mCategoriesData", categoriesData.toString())*/

        categoriesAdapter = CategoriesAdapter()
        /*  categoriesAdapter.setData(categoriesData)*/
        with(binding.catRecyclerView) {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(context)
        }
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
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        viewLifecycleOwner.lifecycleScope.launch {
            GeneralUtils.getUserToken(requireContext()).collect { token ->
                if (token.isNotEmpty()) {
                    categoriesViewModel.fetchCategories(token)

                }
            }
        }
        catObserver(categoriesViewModel!!)
    }

    private fun catObserver(categoriesViewModel: CategoriesViewModel){
        categoriesViewModel.getMediatorLiveData().observe(viewLifecycleOwner, {result->
            if (result.isSuccessful() && !result.getDataList().isNullOrEmpty()){
                categoriesAdapter.setData(result.getDataList() as ArrayList<CategoriesData>, "Category")
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                CategoriesFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}