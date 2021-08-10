package com.e.buynow.view.fragment.authn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.e.buynow.R
import com.e.buynow.network.callback.FragmentChanger
import com.e.buynow.view.activity.AuthnActivity
import com.e.buynow.view.fragment.FragmentTitle



class SignIn : FragmentTitle(), View.OnClickListener {

    lateinit var createAccount:TextView
    lateinit var forgotPassword:TextView
    lateinit var fragmentChanger: FragmentChanger


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fragmentChanger = activity as AuthnActivity

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        setContentView(view)
        return view
    }

    private fun setContentView(view: View){
        createAccount = view.findViewById(R.id.create_account)
        createAccount.setOnClickListener(this)

        forgotPassword = view.findViewById(R.id.forgot_password)
        forgotPassword.setOnClickListener(this)



    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SignIn().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClick(p0: View?) {
        when (p0!!.id){
            R.id.create_account ->fragmentChanger.fragmentChanger(SignUp.newInstance())
            R.id.forgot_password->fragmentChanger.fragmentChanger(ForgottenPassword.newInstance())
        }
    }
}