package com.e.buynow.view.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.e.buynow.R
import com.squareup.picasso.Picasso
import me.relex.circleindicator.CircleIndicator

class OnBoardingAdapter(context: Context, private val arrayList: IntArray, private val mTitleList:IntArray, private val mSubTitleList:IntArray): RecyclerView.Adapter<OnBoardingAdapter.VHClass>() {


   /* private var mcontext: Context = context
    var mItems: IntArray = arrayList
    var mTitle:IntArray=mTitleList
    var mSubTitle:IntArray=mSubTitleList
    var sliderImage: ImageView?=null
    var sliderTitle: TextView?=null
    var sliderSubTitle: TextView?=null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(mcontext).inflate(R.layout.onboarding_item,container,false)!!

        setContentView(view, position)
        container.addView(view,0)
        return  view
    }


    private fun setContentView(view: View, position: Int){

        sliderImage= view.findViewById(R.id.avatar)
        sliderTitle=view.findViewById(R.id.title_tv)
        sliderSubTitle = view.findViewById(R.id.body_tv)


        Picasso.get().load(mItems[position]).into(sliderImage)
        sliderTitle!!.setText(mTitle[position])
        sliderSubTitle!!.setText(mSubTitle[position])
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view == `object`

    }

    override fun getCount(): Int {
        return mItems.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
*/


    private var slider:ImageView?=null
    /*var imageUrl=ArrayList<ImageSlider>()*/
    private var viewPager2: ViewPager2?=null



    /* override fun instantiateItem(container: ViewGroup, position: Int): Any {

         val view = LayoutInflater.from(mContext).inflate(R.layout.holder_slider_image,container,false)

         setContentView(view,position)
         container.addView(view, 0)
         return view
     }
     private fun setContentView(view: View, position: Int){
         slider = view.findViewById(R.id.sliderImage)

         Picasso.get().load(mImageUrl[position]).into(slider)
     }

     override fun isViewFromObject(view: View, `object`: Any): Boolean {
         return  view == `object`
     }

     override fun getCount(): Int {
       return mImageUrl.size
     }

     override fun restoreState(state: Parcelable?, loader: ClassLoader?) {

     }

     override fun saveState(): Parcelable? {

         return null
     }
     override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
         container.removeView(`object` as View)
     } */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.onboarding_item,parent, false)
        return VHClass(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: VHClass, position: Int) {
        holder.bindView(position)
       /* if (position == arrayList.size - 2){
            viewPager2!!.post(holder.runnable)
        }*/
    }
   /* fun setData(imageSliderLIst:ArrayList<ImageSlider>, viewPager2: ViewPager2){
        this.imageUrl=imageSliderLIst
        this.viewPager2=viewPager2
        notifyDataSetChanged()
    }*/



    inner class VHClass(itemView: View) : RecyclerView.ViewHolder(itemView){

        var sliderImage: ImageView? = itemView.findViewById(R.id.avatar)
        var sliderTitle: TextView? = itemView.findViewById(R.id.title_tv)
        private var sliderSubTitle: TextView? = itemView.findViewById(R.id.body_tv)
//        var circlePageIndicator: CircleIndicator?= itemView.findViewById(R.id.circlePageIndicator)



        fun bindView(position:Int) {
            Picasso.get().load(arrayList[position]).into(sliderImage)
            sliderTitle!!.setText(mTitleList[position])
            sliderSubTitle!!.setText(mSubTitleList[position])

        }

       /* val runnable = Runnable {
            run {
                imageUrl.addAll(imageUrl)
            }
        }*/

    }
}

