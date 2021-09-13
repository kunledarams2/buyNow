package com.e.buynow.view.fragment.authn

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.android_dr_app.network.NetworkResponse
import com.e.buynow.view.activity.MainActivity
import com.e.buynow.R
import com.e.buynow.databinding.FragmentPersonalInfoBinding
import com.e.buynow.databinding.FragmentSignInBinding
import com.e.buynow.databinding.FragmentSignUpBinding
import com.e.buynow.network.callback.EndPoint
import com.e.buynow.util.GeneralUtils
import com.e.buynow.util.ToastUtil
import com.e.buynow.view.fragment.FragmentTitle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class SignUp : FragmentTitle() {


    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var endPoint: EndPoint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        val view = binding.root
        setContentView(view)
        return view
    }

    private fun setContentView(view: View) {
        binding.createBtn.setOnClickListener { createAccount() }
        binding.backBtn.setOnClickListener { findNavController().navigate(R.id.action_signUp_to_signIn) }

    }

    private fun createAccount() {
        val eEmail = binding.email.text.toString().trim()
        val eFirstName = binding.firstName.text.toString().trim()
        val eLastName = binding.lastName.text.toString().trim()
        val ePassword = binding.password.text.toString().trim()
        val eUsername = binding.username.text.toString().trim()

        when {
            !Patterns.EMAIL_ADDRESS.matcher(eEmail).matches() || eEmail.isEmpty() -> ToastUtil.showLong(context, "Invalid Email")
            eFirstName.isEmpty() -> ToastUtil.showLong(context, "First name is required....")
            eLastName.isEmpty() -> ToastUtil.showLong(context, "Last name is required....")
            ePassword.isEmpty() -> ToastUtil.showLong(context, "Password is required....")
            eUsername.isEmpty()-> ToastUtil.showLong(context, "Username is required...")
            else -> {
                val params=HashMap<String, Any>()
                params["firstName"]= eFirstName
                params["lastName"]=eLastName
                params["password"] = ePassword
                params["email"] = eEmail
                params["username"] = eUsername


                CoroutineScope(Dispatchers.IO).launch {
                    val response  = endPoint.createUser(params)
                    withContext(Dispatchers.Main){

                        when (response){
                            is NetworkResponse.Success->{
                                ToastUtil.log("SignUp", "Response: ${response.body}")
                                startActivity(Intent(context, MainActivity::class.java))
                            }
                            is NetworkResponse.NetworkError-> ToastUtil.showLong(context, "${response.error}")
                            is NetworkResponse.ApiError -> ToastUtil.log("SignUp", response.code.toString())
                            is NetworkResponse.UnknownError-> GeneralUtils.showAlertMessage(requireActivity(), "Error", response.error!!.message)
                        }

                    }

                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                SignUp().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}