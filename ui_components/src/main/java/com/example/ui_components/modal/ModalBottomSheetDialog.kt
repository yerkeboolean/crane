package com.example.ui_components.modal

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_components.R
import com.example.ui_components.base.BaseBottomSheetDialogItem
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import timber.log.Timber


open class ModalBottomSheetDialog(
    val items: List<BaseBottomSheetDialogItem>,
    val context1: Context,
    val transparent: Boolean = false,
    private val layoutId: Int? = null,
    //if need not hide able
    private val isHideAble: Boolean = true,
    private val peekHide: Int? = null

) : BottomSheetDialog(context1) { // ОБЯЗАТЕЛЬНО нужно, чтоб они унаследовали BaseBottomSheetDialogItem, чтоб dismiss() использовать. его надо воткнуть в клик на айтем и/или кнопку закрытия

    init {
        show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutId?.let { setContentView(it) } ?: setContentView(R.layout.bottom_sheet_recycler)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        if (transparent) {
            val frame = findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            frame?.background = null
            findViewById<LinearLayout>(R.id.bottom_sheet)?.background = context.getDrawable(R.drawable.bg_top_radius_transparent)
        }

        if (!isHideAble && peekHide != null) {
            notHideAble()

        }
        initialise()
    }

    private fun initialise() {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        items.forEach {
            it.actionForClose = { dismiss() }
        }
        groupAdapter.addAll(items)

        val rvBottomSheet = findViewById<RecyclerView>(R.id.rv_bottom_sheet)
        rvBottomSheet?.adapter = groupAdapter

        behavior.state = BottomSheetBehavior.STATE_EXPANDED

    }

    private fun notHideAble() {
        setCanceledOnTouchOutside(false)
        behavior.isHideable = isHideAble
        behavior.peekHeight = peekHide!!
        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Timber.i("On slide")
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                Timber.i("State is $newState")
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
    }
}