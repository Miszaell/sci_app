package com.codeiu.sci_app.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.codeiu.sci_app.dataClases.BranchOffice
import com.codeiu.sci_app.databinding.ItemBranchOfficeBinding

class BranchOfficeViewHolder(view:View): RecyclerView.ViewHolder(view) {

    private val binding = ItemBranchOfficeBinding.bind(view)

    fun render(boModel: BranchOffice){
        binding.boName.text = boModel.name
        binding.boLocation.text = boModel.locationId.toString()
    }
}