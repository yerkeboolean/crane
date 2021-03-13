package com.example.crane.ui.items

import android.annotation.SuppressLint
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ViewDataBinding
import com.example.crane.BR
import com.example.crane.R
import com.example.crane.databinding.ItemCraneTypeBinding
import com.xwray.groupie.databinding.BindableItem

data class CraneTypeUi(
    val id: Int,
    var filled: Boolean = false,
    val name: String
) : BindableItem<ViewDataBinding>() {
    val value = ValueType()
    override fun getLayout(): Int {
        return R.layout.item_crane_type
    }

    @SuppressLint("SetTextI18n")
    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        when (viewBinding) {
            is ItemCraneTypeBinding -> {
                viewBinding.data = this
                var total = 0
                var filled = 0
                value.cranePartsUiMech?.forEach { cranePartsUi ->
                    cranePartsUi.pieces.forEach { cranePartsPiecesUi ->
                        total++
                        if (cranePartsPiecesUi.value.filled == true) {
                            filled++
                        }
                    }
                }
                value.cranePartsUiConstr?.forEach { cranePartsUi ->
                    cranePartsUi.pieces.forEach { cranePartsPiecesUi ->
                        total++
                        if (cranePartsPiecesUi.value.filled == true) {
                            filled++
                        }
                    }
                }
                value.cranePartsUiEl?.forEach { cranePartsUi ->
                    cranePartsUi.pieces.forEach { cranePartsPiecesUi ->
                        total++
                        if (cranePartsPiecesUi.value.filled == true) {
                            filled++
                        }
                    }
                }
                if (filled == 0) {
                    viewBinding.checkTv.text = "Не заполнено"
                } else {
                    viewBinding.checkTv.text = "Заполнено на ${filled * 100 / total}%"
                }
                try {
                    if (total / filled == 1) {
                        this.filled = true
                    }
                } catch (e: Exception) {}
            }
        }
    }

}

class ValueType : BaseObservable() {
    @Bindable
    var cranePartsUiConstr: List<CranePartsUi>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.cranePartsUiConstr)
        }

    @Bindable
    var cranePartsUiMech: List<CranePartsUi>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.cranePartsUiMech)
        }

    @Bindable
    var cranePartsUiEl: List<CranePartsUi>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.cranePartsUiEl)
        }

}