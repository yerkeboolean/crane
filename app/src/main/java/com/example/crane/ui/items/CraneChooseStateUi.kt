package com.example.crane.ui.items

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crane.R
import com.example.ui_components.base.BaseBottomSheetDialogItem
import com.example.crane.databinding.ItemBottomSheetChooseStateBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

data class CraneChooseStateUi(
    val statuses: List<CraneStatusUI>,
    val comment: String? = null,
    val action: ((Int, String?) -> Unit)
) : BaseBottomSheetDialogItem() {

    lateinit var context: Context
    lateinit var binding: ItemBottomSheetChooseStateBinding
    private val items = MutableLiveData<List<CraneStatusUI>>()

    private val checkerAction: ((Int) -> Unit) = { data ->
        items.value?.forEach {
            if (data != it.id)
                it.value.clicked = false
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_bottom_sheet_choose_state
    }

    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        when (viewBinding) {
            is ItemBottomSheetChooseStateBinding -> {
                viewBinding.data = this
                binding = viewBinding
                setData()
            }
        }
    }

    private fun setData() {
        binding.statusesRv.apply {
            val myAdapter = GroupAdapter<GroupieViewHolder>()
            items.value = statuses
            myAdapter.clear()
            myAdapter.addAll(items.value!!)
            statuses.forEach {
                it.action = checkerAction
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = myAdapter
            adapter?.notifyDataSetChanged()
        }
    }

    fun callActionToClose() {
        actionForClose?.let { actionToClose -> actionToClose() }
    }

    fun callActionSetData() {
        findClickedReason()?.let {
            action(it, binding.commentEditText.text.toString())
            actionForClose?.let { actionToClose -> actionToClose() }
        }
    }

    private fun findClickedReason(): Int? {
        items.value?.forEach {
            if (it.value.clicked)
                return it.id
        }
        return null


    }
}