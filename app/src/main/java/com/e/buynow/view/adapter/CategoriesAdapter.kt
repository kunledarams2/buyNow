package com.e.buynow.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.buynow.R
import com.e.buynow.model.CategoriesData
import com.e.buynow.network.callback.ClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_featured.view.*


class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.CatViewHolder>() {

    private var productDataList= ArrayList<CategoriesData>()
    private var mViewType=String()
    private var clickListener:ClickListener<CategoriesData>?=null

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): CategoriesAdapter.CatViewHolder {
      return  when(mViewType){
            "Home" -> CatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_featured, parent, false))
            "Category"-> CatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
          else ->  CatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_featured, parent, false))
      }

    }

    override fun onBindViewHolder(holder: CategoriesAdapter.CatViewHolder, position: Int) {
        when(mViewType){
            "Home" -> holder.bindView(productDataList[position])
            "Category"-> holder.bindView(productDataList[position])
        }


    }

   /* override fun getItemViewType(position: Int): Int {
        return 2
    }*/

    fun setData(data:ArrayList<CategoriesData>, viewType:String){
        this.productDataList = data
        this.mViewType = viewType
        notifyDataSetChanged()
    }

    fun  setClickListener(clickListener: ClickListener<CategoriesData>){
        this.clickListener = clickListener
    }

    override fun getItemCount() = productDataList.size

    inner  class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindView(productData: CategoriesData){

            with(productData){
                itemView.txt_category_name.text = name
                Picasso.get().load(image).into(itemView.img_item)
            }
            itemView.setOnClickListener { clickListener!!.onClick(productData) }
        }
    }
}
