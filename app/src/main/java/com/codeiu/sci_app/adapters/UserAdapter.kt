package com.codeiu.sci_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeiu.sci_app.R
import com.codeiu.sci_app.dataClases.Users

class UserAdapter(private val userList:List<Users>): RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = userList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}