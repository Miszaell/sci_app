package com.codeiu.sci_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.codeiu.sci_app.dataClases.Users
import com.codeiu.sci_app.databinding.FragmentUserFormBinding
import com.codeiu.sci_app.utils.replaceFragment
import com.google.gson.Gson

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UserFormFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var jsonUser: String? = null
    var binding: FragmentUserFormBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(arguments != null){
            jsonUser = requireArguments().getString("jsonUser").toString()
        }
        binding = FragmentUserFormBinding.inflate(inflater, container, false)
        var view = binding!!
        if(jsonUser != null){
            var objGson = Gson()
            var objUser = objGson.fromJson(jsonUser, Users::class.java)

            view.tfName.editText?.setText(objUser.userName)
            view.tfEmail.editText?.setText(objUser.userEmail)

        }
        view.tvReturn.setOnClickListener {
            replaceFragment(UsersFragment(), activity as AppCompatActivity)
        }
        return view.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFormFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}