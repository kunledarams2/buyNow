package com.e.buynow.view.fragment.userprofile

import android.content.Context
import android.hardware.usb.UsbEndpoint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.e.buynow.R
import com.e.buynow.databinding.FragmentPersonalInfoBinding
import com.e.buynow.network.callback.EndPoint
import com.e.buynow.view.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_personal_info.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class PersonalInfo : Fragment() {

    private var _binding:FragmentPersonalInfoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var endpoint: EndPoint

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }


//    lateinit var binding: FragmentPersonalInfoBinding
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

//       val view = inflater.inflate(R.layout.fragment_personal_info, container, false)
        _binding = FragmentPersonalInfoBinding.inflate(inflater,container,false)
        val view = binding.root
        setContentView(view)
        return view
    }

    private fun setContentView(view: View){
//        _binding.back_btn.setOnClickListener {
//            findNavController().navigate(R.id.action_personalInfo_to_profileHomePage) }
    }

    private fun updateUserInfo(){
        val firstName = binding.firstName.text.toString().trim()
        val lastName = binding.lastName.text.toString().trim()
        val email = binding.email.text.toString().trim()
        val phoneNumber =binding.phoneNumber.text.toString().trim()

        val params = HashMap<String, Any>()
        params["firstName"] = firstName
        params["lastName"] = lastName
        params["email"] = email
        params["phoneNumber"] = phoneNumber

        CoroutineScope(Dispatchers.IO).launch {
            val response = endpoint.loginUser(params)

        }

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PersonalInfo().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}