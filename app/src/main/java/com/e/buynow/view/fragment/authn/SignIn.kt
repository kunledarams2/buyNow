package com.e.buynow.view.fragment.authn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android_dr_app.network.NetworkResponse
import com.e.buynow.MainActivity
import com.e.buynow.R
import com.e.buynow.network.callback.EndPoint
import com.e.buynow.network.callback.FragmentChanger
import com.e.buynow.util.ToastUtil
import com.e.buynow.view.activity.AuthnActivity
import com.e.buynow.view.fragment.FragmentTitle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sign_in.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AndroidEntryPoint
class SignIn : FragmentTitle(), View.OnClickListener {

    lateinit var createAccount: TextView
    lateinit var forgotPassword: TextView
    lateinit var fragmentChanger: FragmentChanger

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
        setContentView(view)
        return view
    }

    private fun setContentView(view: View) {
        createAccount = view.findViewById(R.id.create_account)
        createAccount.setOnClickListener(this)

        forgotPassword = view.findViewById(R.id.forgot_password)
        forgotPassword.setOnClickListener(this)

        view.login_btn.setOnClickListener { loginUser() }

    }

    private fun loginUser() {
        val email = requireView().email.text.toString().trim()
        val password = requireView().password.text.toString().trim()

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
                                ToastUtil.log("SignIn", "-_-_-_-${response.body.message}")
                                startActivity(Intent(context, MainActivity::class.java))
                            }
                            is NetworkResponse.ApiError -> {
                                ToastUtil.log("SignIn", "-_-_-_-${response.body.message}")
                            }
                            is NetworkResponse.NetworkError -> {
                                ToastUtil.showLong(context, "${response.error.message}")
                            }
                            is NetworkResponse.UnknownError -> ToastUtil.showLong(
                                context,
                                "Network Error"
                            )
                        }
                        /*  if (response.isSuccessful){
                              val obj = JSONObject(response.body()!!.string())

                          } else{
                              ToastUtil.log("SignIn", "-error_-_-_-${JSONObject(response.errorBody()!!.string())}")
                          }*/
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
        when (p0!!.id) {
            R.id.create_account -> fragmentChanger.fragmentChanger(SignUp.newInstance())
            R.id.forgot_password -> fragmentChanger.fragmentChanger(ForgottenPassword.newInstance())
        }
    }
}