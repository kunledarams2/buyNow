package com.e.buynow.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.buynow.R
import com.e.buynow.model.ProductData
import com.e.buynow.network.callback.ClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular.view.*

class HomeProductAdapter:RecyclerView.Adapter<HomeProductAdapter.ProductViewHolder>() {

    private var productDataList= ArrayList<ProductData>()
    private var clickListener:ClickListener<ProductData>?=null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeProductAdapter.ProductViewHolder {
     return  ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false))
    }

    override fun onBindViewHolder(holder: HomeProductAdapter.ProductViewHolder, position: Int) {
     holder.bindView(productDataList[position])
    }

    fun setData(data:ArrayList<ProductData>){
        this.productDataList = data
        notifyDataSetChanged()
    }
    fun setItemClickListener(clickListener: ClickListener<ProductData>){
        this.clickListener = clickListener
    }

    override fun getItemCount() = productDataList.size

    inner  class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindView(productData: ProductData){

            with(productData){
                itemView.txt_pop_item_name.text = name
                itemView.txt_pop_item_desc.text = description
                itemView.txt_pop_item_cost.text = "₦$price"

                for(image in images){
                    Log.d("HomeAdapter", image)
                    Picasso.get().load(image).into(itemView.item_pop_image)
                }


            }
            itemView.setOnClickListener { clickListener!!.onClick(productData) }
        }
    }
}