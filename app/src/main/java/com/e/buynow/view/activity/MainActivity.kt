package com.e.buynow.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.datastore.preferences.preferencesDataStore
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.e.buynow.R
import com.e.buynow.databinding.ActivityMainBinding
import com.e.buynow.util.AppConstants
import com.e.buynow.util.GeneralUtils
import com.e.buynow.view.activity.interfaces.FragmentListener
import com.e.buynow.view.activity.interfaces.FullImageListener
import com.e.buynow.view.fragment.BaseFragment
import com.e.buynow.view.fragment.navigation.*
import com.e.buynow.view.fragment.userprofile.ProfileHomePage
import com.google.android.material.navigation.NavigationBarView
import com.vertex5.vertex5.view.fragment.main.FragmentHomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentListener, FullImageListener {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FragmentHomeAdapter
    private val listFragments = LinkedList<BaseFragment?>()
    private var home:HomeFragment?=null
    private var cart:CartFragment?=null
    private var fav:FavFragment?=null
    private var profile: UserProfileFragment?=null
    private var notify:NotificationFragment?=null
    private lateinit var toolbar: Toolbar
    private var fullImageFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        initialisePrequisites()
        setupActionBar()
        setupHomeActionBar()
        setContentView(view)
        initialiseWidget()
    }

    private fun initialisePrequisites() {
        home = HomeFragment.newInstance()
        cart = CartFragment.newInstance()
        fav = FavFragment.newInstance()
        profile = UserProfileFragment.newInstance()
        notify = NotificationFragment.newInstance()

        listFragments.add(home)
        listFragments.add(cart)
        listFragments.add(fav)
        listFragments.add(profile)
        listFragments.add(notify)
    }

    private fun setupActionBar(){
        with(binding){
            toolbar = appbarHome.homeToolbar
            setSupportActionBar(toolbar)
        }
    }

    private fun setupHomeActionBar() {
        val ab = supportActionBar
        ab?.elevation =0f
        binding.appbarHome.layoutHome?.visibility = View.GONE
        binding.appbarHome.layoutOther?.visibility = View.VISIBLE
        ab!!.setDisplayShowCustomEnabled(true) // enable overriding the default toolbar layout
        ab.setDisplayHomeAsUpEnabled(false)
        ab.setDisplayShowCustomEnabled(false) // enable overriding the default toolbar layout
        ab.setDisplayShowTitleEnabled(false)
    }

    private fun setupOtherActionBar() {
        Log.d(TAG, "Inside .......setupOtherActionBar...............")
        binding.appbarHome.layoutHome?.visibility = View.GONE
        binding.appbarHome.layoutOther?.visibility = View.VISIBLE
        val ab = supportActionBar
        ab?.elevation =0f
        ab!!.setDisplayShowHomeEnabled(true) // show or hide the default home button
        ab.setDisplayHomeAsUpEnabled(true)
        ab.setDisplayShowCustomEnabled(true) // enable overriding the default toolbar layout
        ab.setDisplayShowTitleEnabled(false) // disable the default title element here (for centered title)
    }

    override fun showFullImages(img: Array<String>) {
        fullImageFlag = true
        setupOtherActionBar()
        with(binding){
            listFullImg.visibility = View.VISIBLE

        }
    }

    private fun initialiseWidget() {
        with(binding) {
            adapter = FragmentHomeAdapter(
                this@MainActivity,
                supportFragmentManager, listFragments
            )

            listFullImg.layoutManager = LinearLayoutManager(this@MainActivity,
                RecyclerView.VERTICAL, false)

            with(mainContent) {
                bottomNavigationMain.setOnItemSelectedListener(navListener)
                pager.adapter = adapter
                pager.addOnPageChangeListener(pageChangeListener)
                appbarHome.imgNotification.setOnClickListener {
                    pager.currentItem = 4
                }
            }
        }
    }

    override fun hideBNV() {
        removeBottomNav()
    }

    override fun showBNV() {
        binding.mainContent.bottomNavigationMain.visibility = View.VISIBLE
    }

    private fun removeBottomNav() {
        Log.d(TAG, "Inside......................")
        binding.mainContent.bottomNavigationMain.visibility = View.GONE
    }



    private val navListener =
        NavigationBarView.OnItemSelectedListener { menuItem ->
            Log.d(TAG, "onItemSelected: pager size ->")

            with(binding.mainContent) {
                when (menuItem.itemId) {
                    R.id.navigation_home -> {
                        pager.currentItem = 0
                        return@OnItemSelectedListener true
                    }

                    R.id.navigation_cart -> {
                        pager.currentItem = 1

                        return@OnItemSelectedListener true
                    }
                    R.id.navigation_fav -> {
                        pager.currentItem = 2
                        return@OnItemSelectedListener true
                    }
                    R.id.navigation_profile -> {
                        pager.currentItem = 3
                        return@OnItemSelectedListener true
                    }
                }
                false
            }
        }

    private val pageChangeListener: ViewPager.OnPageChangeListener = object :
        ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {}

        override fun onPageSelected(position: Int) {
            with(binding.mainContent) {
                when {
                    adapter.getPageTitle(position) == AppConstants.FRAG_HOME -> {
                        setupHomeActionBar()
                    }
                    adapter.getPageTitle(position) == AppConstants.FRAG_CART -> {
                        setupOtherActionBar()
                    }
                    adapter.getPageTitle(position) == AppConstants.FRAG_FAVOURITE -> {
                        setupOtherActionBar()
                        //bottomNavigationMain.visibility = View.GONE
                    }
                    adapter.getPageTitle(position) == AppConstants.FRAG_PROFILE -> {
                        setupOtherActionBar()

//                        bottomNavigationMain.visibility = View.GONE
                    }
                    adapter.getPageTitle(position) == AppConstants.FRAG_NOTIFICATIONS -> {
                        setupOtherActionBar()

                    }
                }
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    fun getToolbar(label:String) {
        if (label == "HomeFragment" || label == "BleDialog"){
            setupActionBar()
        }else{
            setupOtherActionBar()
        }
    }


    override fun onBackPressed() {
        with(binding) {
            if (fullImageFlag) listFullImg.visibility = View.GONE
        }
    }
}