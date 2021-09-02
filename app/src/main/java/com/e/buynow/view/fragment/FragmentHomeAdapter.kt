package com.vertex5.vertex5.view.fragment.main

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.e.buynow.util.AppConstants
import com.e.buynow.view.fragment.BaseFragment
import java.util.*

class FragmentHomeAdapter(val context: Context, fragmentManager: FragmentManager, val fragList: LinkedList<BaseFragment?>)
    :FragmentPageAdapter(fragmentManager, fragList) {

    init{
//        TODO("Not yet implemented")
        if (!pageTitles.isEmpty()){
            pageTitles.clear()
        }
            pageTitles.add(AppConstants.FRAG_HOME)
            pageTitles.add(AppConstants.FRAG_CART)
            pageTitles.add(AppConstants.FRAG_FAVOURITE)
            pageTitles.add(AppConstants.FRAG_PROFILE)
            pageTitles.add(AppConstants.FRAG_NOTIFICATIONS)
    }
}