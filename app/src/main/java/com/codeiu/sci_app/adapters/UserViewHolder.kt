package com.codeiu.sci_app.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.codeiu.sci_app.dataClases.Users
import com.codeiu.sci_app.databinding.ItemUserBinding

class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemUserBinding.bind(view)

    fun render(userModel: Users){
        binding.userName.text = userModel.userName
        binding.userEmail.text = userModel.userEmail
    }
}