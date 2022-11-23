package com.codeiu.sci_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.codeiu.sci_app.R
import com.codeiu.sci_app.dataClases.Users
import com.google.gson.Gson
import com.codeiu.sci_app.fragments.UserFormFragment
import com.codeiu.sci_app.utils.replaceFragment

class UserAdapter(private val userList:List<Users>, private val dataSet: Array<Users>): RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = userList[position]

        holder.render(item)
        holder.itemView.setOnClickListener {
            var jsonObj = Gson()
            var jsonUser = jsonObj.toJson(dataSet[position])
            var bundle = bundleOf("jsonUser" to jsonUser)

            val activity = it.context as AppCompatActivity
            replaceFragment(UserFormFragment(), activity, bundle)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}