package com.example.crane.ui.items

import androidx.databinding.ViewDataBinding
import com.example.crane.R
import com.example.crane.databinding.ItemChooseCraneTypeBinding
import com.xwray.groupie.databinding.BindableItem

data class ChooseCraneTypeUi(
    val id: Int,
    val imgId: Int,
    val name: String
) : BindableItem<ViewDataBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_choose_crane_type
    }

    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        when (viewBinding) {
            is ItemChooseCraneTypeBinding -> {
                viewBinding.data = this
            }
        }
    }

}