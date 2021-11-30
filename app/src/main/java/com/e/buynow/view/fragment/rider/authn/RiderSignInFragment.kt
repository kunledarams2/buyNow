package com.e.buynow.view.fragment.rider.authn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android_dr_app.network.NetworkResponse
import com.e.buynow.R
import com.e.buynow.databinding.FragmentRiderSignInBinding
import com.e.buynow.network.callback.EndPoint
import com.e.buynow.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RiderSignInFragment : Fragment(){
    private val binding : FragmentRiderSignInBinding by lazy {
        FragmentRiderSignInBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var endPoint: EndPoint

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setContentView()
        return binding.root
    }

    private fun setContentView(){
        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_riderSignInFragment_to_riderOnboarding2Fragment2)
        }
        binding.login.setOnClickListener { loginRider() }
    }

    private fun loginRider(){
        val riderId = binding.driverId.text.toString()
        val riderPassword = binding.driverPassword.text.toString()

        when {
            riderId.isEmpty() -> ToastUtil.showLong(context, "Your ID is required")
            riderPassword.isEmpty() -> ToastUtil.showLong(context, "Your password is required")
            else -> {
                val params = HashMap<String, Any>()
                params["password"] =  riderPassword
                params["id"] = riderId

                CoroutineScope(Dispatchers.IO).launch {
                    val response = endPoint.loginRider(params)

                    withContext(Dispatchers.Main) {
                        when (response) {
                            is NetworkResponse.Success -> {
                                ToastUtil.log("SignIn", "-_-_-_-${response.body}")
                                findNavController().navigate(R.id.action_riderSignInFragment_to_loginSuccessFragment)
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

    companion object {
        @JvmStatic
        fun newInstance() =
            RiderSignInFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}