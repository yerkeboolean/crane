package com.example.crane.ui.items

import android.content.Context
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ViewDataBinding
import com.example.crane.BR
import com.example.crane.R
import com.example.crane.databinding.ItemCranePartPiecesBinding
import com.example.ui_components.modal.ModalBottomSheetDialog
import com.xwray.groupie.databinding.BindableItem

data class CranePartPiecesUi(
    val name: String,
    var comment: String? = null,
    val actionToCheck: (() -> Unit)? = null,
    val hideKeyBoard: (() -> Unit)? = null
) : BindableItem<ViewDataBinding>() {
    val value = ValuePartPieces()
    lateinit var binding: ItemCranePartPiecesBinding
    override fun getLayout(): Int {
        return R.layout.item_crane_part_pieces
    }

    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        when (viewBinding) {
            is ItemCranePartPiecesBinding -> {
                viewBinding.data = this
                binding = viewBinding
                binding.answerEditText.doOnTextChanged { text, _, _, _ ->
                    comment = text.toString()
                    value.filled = comment!!.isNotEmpty()
                    actionToCheck?.let { it() }
                }
            }
        }

    }

    fun onSatisfactoryClick() {
        comment = null
        value.satisfactory = true
        value.unsatisfactory = false
        value.filled = true
        hideKeyBoard?.let { it() }
        actionToCheck?.let { it() }
    }

    fun onUnsatisfactoryClick() {
        comment = null
        value.satisfactory = false
        value.unsatisfactory = true
        value.filled = false
        actionToCheck?.let { it() }
        binding.answerEditText.setText("")
    }

}

class ValuePartPieces : BaseObservable() {
    @Bindable
    var filled: Boolean? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.filled)
        }

    @Bindable
    var satisfactory: Boolean? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.satisfactory)
        }

    @Bindable
    var unsatisfactory: Boolean? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.unsatisfactory)
        }

}