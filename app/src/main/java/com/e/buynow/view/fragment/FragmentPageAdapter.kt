package com.vertex5.vertex5.view.fragment.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.e.buynow.view.fragment.BaseFragment
import java.util.*
import kotlin.collections.HashSet
import kotlin.collections.LinkedHashSet

abstract class FragmentPageAdapter(
    fragmentManager: FragmentManager,
    protected val fragmentList: LinkedList<BaseFragment?>):
    FragmentStatePagerAdapter(fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    protected val pageTitles:HashSet<String> = LinkedHashSet<String>()

    override fun getCount(): Int {
        return pageTitles.size
    }

    override fun getItemPosition(obj: Any): Int {
        return POSITION_NONE
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]!!
    }

    override fun getPageTitle(position: Int): CharSequence {
        return pageTitles.elementAt(position)
    }
}