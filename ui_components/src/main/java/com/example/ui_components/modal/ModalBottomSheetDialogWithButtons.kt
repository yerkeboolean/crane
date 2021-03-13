package com.example.ui_components.modal

import android.content.Context
import android.content.res.Resources
import android.view.View
import com.example.ui_components.base.BaseBottomSheetDialogItem
import kotlinx.android.synthetic.main.bottom_sheet_recycler_with_buttons.*

class ModalBottomSheetDialogWithButtons(
    items: List<BaseBottomSheetDialogItem>,
    context1: Context,
    private val actionButtonApply: (() -> Unit)? = null,
    private val actionButtonClear: (() -> Unit)? = null,
    headerText: String,
    fullScreen: Boolean = false,
    transparent: Boolean = false,
    layoutId: Int? = null,
    isHideAble: Boolean = true,
    peekHide: Int? = null

) : ModalBottomSheetDialog(
    items = items,
    context1 = context1,
    transparent = transparent,
    layoutId = layoutId,
    isHideAble = isHideAble,
    peekHide = peekHide
) {

    init {
        btn_apply.setOnClickListener {
            actionButtonApply?.let { it() }
            dismiss()
        }

        btn_clear.setOnClickListener {
            actionButtonClear?.let { it() }
        }

        ic_dismiss.setOnClickListener {
            dismiss()
        }
        if (fullScreen)
            rv_bottom_sheet.minimumHeight = Resources.getSystem().displayMetrics.heightPixels

        text_view.text = headerText
    }

    fun setStateOfButtonAtBottomApply(state: Boolean) {
        if (!state) {
            btn_apply.visibility = View.GONE
            rv_bottom_sheet.setPadding(0, 0, 0, 0)
        } else {
            btn_apply.visibility = View.VISIBLE
            rv_bottom_sheet.setPadding(0, 0, 0, 140)
        }
    }

    fun setStateOfButtonAtBottomBoth(state: Boolean) {
        if (!state) {
            btn_apply.visibility = View.GONE
            btn_clear.visibility = View.GONE
            rv_bottom_sheet.setPadding(0, 0, 0, 0)
        } else {
            btn_apply.visibility = View.VISIBLE
            btn_clear.visibility = View.VISIBLE
            rv_bottom_sheet.setPadding(0, 0, 0, 140)
        }
    }

}