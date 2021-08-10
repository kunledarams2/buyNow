package com.e.buynow.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.e.buynow.R
import com.e.buynow.callback.FragmentChanger
import com.e.buynow.view.fragment.FragmentTitle
import com.e.buynow.view.fragment.authn.SignIn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthnActivity : AppCompatActivity(), FragmentChanger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authn)

        fragmentChanger(SignIn.newInstance())
    }

    override fun fragmentChanger(fragmentTitle: FragmentTitle) {

//        currentPageTitle=fragmentTitle.title
        changeView(fragmentTitle)
    }

    private fun changeView(fragment: Fragment){
        val fragmentManager: FragmentManager =supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.frame, fragment)
            .commit()

    }
}