package com.codeiu.sci_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeiu.sci_app.R
import com.codeiu.sci_app.adapters.UserAdapter
import com.codeiu.sci_app.dataClases.UserProvider
import com.codeiu.sci_app.databinding.FragmentUsersBinding
import com.codeiu.sci_app.utils.replaceFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UsersFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var binding: FragmentUsersBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        var view = binding!!
        binding!!.recyclerUsers.layoutManager = LinearLayoutManager(container!!.context)

        binding!!.recyclerUsers.adapter = UserAdapter(UserProvider.userList, UserProvider.userList.toTypedArray())

        view.btnAdd.setOnClickListener {
            replaceFragment(UserFormFragment(), activity as AppCompatActivity)
        }


        return view.root
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UsersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}