package com.codeiu.sci_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.codeiu.sci_app.R
import com.codeiu.sci_app.dataClases.BranchOffice
import com.codeiu.sci_app.databinding.FragmentBranchOfficeFormBinding
import com.codeiu.sci_app.utils.replaceFragment
import com.google.gson.Gson

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BranchOfficeFormFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentBranchOfficeFormBinding
    private lateinit var jsonBo: String
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
        binding = FragmentBranchOfficeFormBinding.inflate(inflater, container, false)
        val items = listOf("Mexico", "USA")
        val adapter = context?.let { ArrayAdapter(it, R.layout.dropdown_item, items) }

        binding.dropdownMenu.setAdapter(adapter)

        if (arguments != null){
            jsonBo = requireArguments().getString("jsonBo").toString()
        }
        if (jsonBo != null) {
            var objGson = Gson()
            var objBo = objGson.fromJson(jsonBo, BranchOffice::class.java)

            binding.tfName.editText?.setText(objBo.name)
            binding.tfLocationId.editText?.setText(objBo.locationId.toString())
        }

        binding.tvReturn.setOnClickListener {
            replaceFragment(BranchOfficesFragment(), activity as AppCompatActivity)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BranchOfficeFormFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}