package com.e.buynow.view.fragment.authn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android_dr_app.network.NetworkResponse
//import com.e.buynow.MainActivity
import com.e.buynow.R
import com.e.buynow.databinding.FragmentSignInBinding
import com.e.buynow.network.callback.EndPoint

import com.e.buynow.network.callback.FragmentChanger
import com.e.buynow.util.ToastUtil

import com.e.buynow.view.activity.AuthnActivity
import com.e.buynow.view.activity.MainActivity
import com.e.buynow.view.fragment.FragmentTitle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject


@AndroidEntryPoint
class SignIn : FragmentTitle(), View.OnClickListener {

    lateinit var createAccount:TextView
    lateinit var forgotPassword:TextView
    lateinit var fragmentChanger: FragmentChanger
    private lateinit var binding:FragmentSignInBinding

    @Inject
    lateinit var endPoint: EndPoint


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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSignInBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        setContentView()
    }

    private fun setContentView(){
        with(binding) {
            createAccount.setOnClickListener(this@SignIn)
            forgotPassword.setOnClickListener(this@SignIn)
            loginBtn.setOnClickListener { loginUser() }
        }

    }

    private fun loginUser(){
        with(binding) {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            when {
                email.isEmpty() -> ToastUtil.showLong(context, "Email is required...")
                password.isEmpty() -> ToastUtil.showLong(context, "Password is required...")
                else -> {
                    val params = HashMap<String, Any>()
                    params["password"] = password
                    params["email"] = email

                    CoroutineScope(Dispatchers.IO).launch {
                        val response = endPoint.loginUser(params)
                        withContext(Dispatchers.Main) {
                            when (response) {
                                is NetworkResponse.Success -> {

                                    ToastUtil.log("SignIn", "-_-_-_-${response.body}")
                                    startActivity(Intent(context, MainActivity::class.java))
                                }
                                is NetworkResponse.UnknownError -> ToastUtil.log(
                                    "SignIn",
                                    "-error_-_-_-${response.error}"
                                )
                                is NetworkResponse.NetworkError -> ToastUtil.log(
                                    "SignIn",
                                    "-error_-_-_-${response.error}"
                                )
                                is NetworkResponse.ApiError -> ToastUtil.log(
                                    "SignIn",
                                    "-error_-_-_-${response.body.message}"
                                )

                            }
                        }
                    }
                }
            }
        }
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