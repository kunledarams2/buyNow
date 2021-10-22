package com.e.buynow.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.buynow.R
import com.e.buynow.network.callback.ClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_item.view.*

class ImageSliderAdapter:RecyclerView.Adapter<ImageSliderAdapter.ImageVH>() {

    private var imageList = ArrayList<String>()
    private var setClickListener:ClickListener<String>?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderAdapter.ImageVH {
       return  ImageVH(LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false))
    }

    override fun onBindViewHolder(holder: ImageSliderAdapter.ImageVH, position: Int) {
        holder.bindView(imageList[position])

    }

    override fun getItemCount()=  imageList.size

    fun setImageData(imageData:ArrayList<String>){
        this.imageList = imageData
        notifyDataSetChanged()
    }
    fun setOnItemClickListener(clickListener: ClickListener<String>){
        this.setClickListener = clickListener
    }

    inner class  ImageVH(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindView(imageUrl:String){
           Picasso.get().load(imageUrl).into(itemView.image_holder)
            itemView.image_holder.setOnClickListener { setClickListener!!.onClick(imageUrl) }
        }
    }
}