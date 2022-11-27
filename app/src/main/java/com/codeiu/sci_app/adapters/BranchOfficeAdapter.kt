package com.codeiu.sci_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.codeiu.sci_app.dataClases.BranchOffice
import com.codeiu.sci_app.R
import com.codeiu.sci_app.fragments.BranchOfficeFormFragment
import com.codeiu.sci_app.utils.replaceFragment
import com.google.gson.Gson

class BranchOfficeAdapter(private val boList:List<BranchOffice>): RecyclerView.Adapter<BranchOfficeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchOfficeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_branch_office, parent, false)
        return BranchOfficeViewHolder(layoutInflater)

    }

    override fun onBindViewHolder(holder: BranchOfficeViewHolder, position: Int) {
        val item = boList[position]

        holder.render(item)
        holder.itemView.setOnClickListener{
            var jsonObj = Gson()
            var jsonBo = jsonObj.toJson(boList[position])
            var bundle = bundleOf("jsonBo" to jsonBo)

            val activity = it.context as AppCompatActivity
            replaceFragment(BranchOfficeFormFragment(), activity, bundle)
        }
    }

    override fun getItemCount(): Int {
        return  boList.size
    }
}