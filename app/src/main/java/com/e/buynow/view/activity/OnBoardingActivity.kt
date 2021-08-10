package com.e.buynow.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.e.buynow.MainActivity
import com.e.buynow.R
import com.e.buynow.view.adapter.OnBoardingAdapter
import me.relex.circleindicator.CircleIndicator3
import java.util.*

class OnBoardingActivity : AppCompatActivity() {

    private var currentPage = 0
    private val numPages = 0

    private var mViewPager: ViewPager2?=null
    private var sliderHandler = Handler()
    private var onBoardingSliderAdapter: OnBoardingAdapter?=null
//    private var circleIndicator:CirclePageIndicator?=null
    private var circlePageIndicator: CircleIndicator3? = null
    private var mGetStarted: AppCompatButton?=null
    private var mNextSlide: ImageButton?=null
    private var mSkip: TextView? = null


    private val SLIDES =
        intArrayOf(R.drawable.onboarding_screen_one, R.drawable.onboarding_screen_two, R.drawable.onboarding_screen_three)
    private val TITLES =
        intArrayOf(R.string.slide_title_1, R.string.slide_title_2, R.string.slide_title_3)
    private val DESCS =
        intArrayOf(R.string.slide_desc_1, R.string.slide_desc_2, R.string.slide_desc_3)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        var isLast = false
        mViewPager = findViewById(R.id.sliderViewPager)
        mNextSlide = findViewById(R.id.next_btn)
        mSkip = findViewById(R.id.skip_tv)
        mGetStarted = findViewById(R.id.get_started_btn)
        circlePageIndicator = findViewById(R.id.circlePageIndicator)



        onBoardingSliderAdapter = OnBoardingAdapter(this, SLIDES, TITLES ,DESCS)
        mViewPager!!.adapter = onBoardingSliderAdapter

        mViewPager!!.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                sliderHandler.removeCallbacks(runnable)
                sliderHandler.postDelayed(runnable, 5000)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                currentPage = position
                isLast=position==SLIDES.size -1

                Log.d("currentPage", currentPage.toString())

                if (isLast){
                    mGetStarted!!.visibility= View.VISIBLE
                    mNextSlide!!.visibility=View.GONE
                    mSkip!!.visibility = View.GONE


                } else{
                    mGetStarted!!.visibility= View.GONE
                    mNextSlide!!.visibility=View.VISIBLE
                    mSkip!!.visibility = View.VISIBLE
                }
            }
        })



        val handler = Handler()

        val swipTimer = Timer()
        swipTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 5000, 5000)


        circlePageIndicator!!.setViewPager(mViewPager)

        mNextSlide!!.setOnClickListener {
            if (mViewPager!!.adapter!!.itemCount > currentPage){
                mViewPager!!.setCurrentItem(currentPage + 1, true)
            }

        }

        mGetStarted!!.setOnClickListener {
            startActivity(Intent(this, AuthnActivity::class.java))
        }




    }

    val runnable = Runnable {
        if (numPages == currentPage) {
            currentPage = 0
            Log.d("currentPage", currentPage.toString())
        }
        mViewPager!!.setCurrentItem(currentPage++, false)
//            Log.d("currentPage", currentPage.toString())
    }

    override fun onBackPressed() {

    }
}