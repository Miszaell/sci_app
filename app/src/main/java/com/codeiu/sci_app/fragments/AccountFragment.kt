package com.codeiu.sci_app.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.codeiu.sci_app.R
import com.codeiu.sci_app.databinding.FragmentAccountBinding
import com.codeiu.sci_app.utils.readOnly

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AccountFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var uname: String
    private lateinit var imGallery: ImageView
    private  var galleryCode = 100
    private lateinit var imageUri: Uri
    private lateinit var binding: FragmentAccountBinding
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
        binding = FragmentAccountBinding.inflate(inflater, container, false)

        if (arguments != null) {
            uname = requireArguments().getString("uname").toString()
            binding.tfName.editText?.setText(uname)
            readOnly(binding.tetName, true)
            readOnly(binding.tetEmail, true)
        }

        imGallery = binding.imProfile
       binding.btnEdit.setOnClickListener{

           readOnly(binding.tetName, false)
           readOnly(binding.tetEmail, false)

           buttonsVisibility(true)
           photoListener()
           saveListener()
       }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==RESULT_OK && requestCode==galleryCode) {
                imageUri = data?.data!!
                imGallery.setImageURI(imageUri)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun photoListener(){
        binding.imgUser.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, galleryCode)
        }
    }

    private fun saveListener(){
        binding.btnSave.setOnClickListener {

            //post request here

            binding.imgUser.setOnClickListener(null)
            readOnly(binding.tetName, true)
            readOnly(binding.tetEmail, true)
            buttonsVisibility(false)
        }
    }

    private fun buttonsVisibility(visibility: Boolean){
            binding.btnEdit.visibility = if (visibility) View.GONE else View.VISIBLE
            binding.btnSave.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}