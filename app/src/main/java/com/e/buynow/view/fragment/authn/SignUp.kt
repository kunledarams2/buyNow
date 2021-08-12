package com.e.buynow.view.fragment.authn

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.android_dr_app.network.NetworkResponse
import com.e.buynow.MainActivity
import com.e.buynow.R
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

    lateinit var mPassword: EditText
    lateinit var mLastName: EditText
    lateinit var mFirstName: EditText
    lateinit var mEmail: EditText
    lateinit var mUsername: EditText
    lateinit var createBtn:AppCompatButton

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
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        setContentView(view)
        return view
    }

    private fun setContentView(view: View) {
        mEmail = view.findViewById(R.id.email)
        mFirstName = view.findViewById(R.id.first_name)
        mLastName = view.findViewById(R.id.last_name)
        mPassword = view.findViewById(R.id.password)
        mUsername = view.findViewById(R.id.username)

        createBtn =view.findViewById(R.id.createBtn)
        createBtn.setOnClickListener { createAccount() }

    }

    private fun createAccount() {
        val eEmail = mEmail.text.toString().trim()
        val eFirstName = mFirstName.text.toString().trim()
        val eLastName = mLastName.text.toString().trim()
        val ePassword = mPassword.text.toString().trim()
        val eUsername = mUsername.text.toString().trim()

        when {
            !Patterns.EMAIL_ADDRESS.matcher(eEmail).matches() || eEmail.isEmpty() -> ToastUtil.showLong(context, "Invalid Email")
            eFirstName.isEmpty() -> ToastUtil.showLong(context, "First name is required....")
            eLastName.isEmpty() -> ToastUtil.showLong(context, "Last name is required....")
            ePassword.isEmpty() -> ToastUtil.showLong(context, "Password is required....")
            eUsername.isEmpty()-> ToastUtil.showLong(context, "Username is reaquired...")
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

                    /*    if (response.isSuccessful){

                            val obj = JSONObject(response.body()!!.string())

                        } else  ToastUtil.log("SignUp", "Error--_--_--_-__-_  ${JSONObject(response.errorBody()!!.string())}")*/
                    }

                }
            }
        }

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