package com.codeiu.sci_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeiu.sci_app.R
import com.codeiu.sci_app.adapters.BranchOfficeAdapter
import com.codeiu.sci_app.dataClases.BranchOfficeProvider
import com.codeiu.sci_app.databinding.FragmentBranchOfficesBinding
import com.codeiu.sci_app.utils.replaceFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BranchOfficesFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentBranchOfficesBinding

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
        binding = FragmentBranchOfficesBinding.inflate(inflater, container, false)
        binding.recyclerBranchOffice.layoutManager = LinearLayoutManager(container!!.context)
        binding.recyclerBranchOffice.adapter = BranchOfficeAdapter(BranchOfficeProvider.branchOfficeList)

        binding.btnAdd.setOnClickListener {
            replaceFragment(BranchOfficeFormFragment(), activity as AppCompatActivity)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BranchOfficesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}