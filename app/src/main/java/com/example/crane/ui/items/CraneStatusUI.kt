package com.example.crane.ui.items

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ViewDataBinding
import com.example.crane.BR
import com.example.crane.R
import com.example.crane.databinding.ItemBottomSheetStatusBinding
import com.xwray.groupie.databinding.BindableItem

data class CraneStatusUI(
    val id: Int,
    val name: String,
    var action: ((Int) -> Unit)? = null

) : BindableItem<ViewDataBinding>() {
    val value = ValueCraneStatus()
    override fun getLayout(): Int {
        return R.layout.item_bottom_sheet_status
    }

    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        when (viewBinding) {
            is ItemBottomSheetStatusBinding -> {
                viewBinding.data = this
            }
        }
    }

    fun onClick() {
        value.clicked = !value.clicked
        action?.let { it(this.id) }

    }


}

class ValueCraneStatus : BaseObservable() {
    @Bindable
    var clicked: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.clicked)
        }

}