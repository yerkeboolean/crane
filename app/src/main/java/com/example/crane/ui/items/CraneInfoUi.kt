package com.example.crane.ui.items

import androidx.core.widget.doOnTextChanged
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crane.R
import com.example.crane.databinding.ItemCraneInfoBinding
import com.example.crane.databinding.ItemCraneInfoSubquestionsBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.databinding.BindableItem
import java.io.Serializable
data class CraneInfoUi(
    val required: Boolean,
    val question: String,
    val subQuestions: List<CraneInfoSubQuestionsUi>,
    var answer: String? = null
) : BindableItem<ViewDataBinding>(), Serializable {
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    lateinit var binding: ItemCraneInfoBinding
    override fun getLayout(): Int {
        return R.layout.item_crane_info
    }


    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        when (viewBinding) {
            is ItemCraneInfoBinding -> {

                viewBinding.data = this
                binding = viewBinding
                initRecyclerView()
                binding.answerEditText.doOnTextChanged { text, _, _, _ ->
                    answer = text.toString()
                }
            }
        }
    }

    private fun initRecyclerView() {
        groupAdapter.clear()
        groupAdapter.addAll(subQuestions)
        binding.subQuestionsRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = groupAdapter
            adapter?.notifyDataSetChanged()
        }
    }

}
data class CraneInfoSubQuestionsUi(
    val question: String,
    var answer: String? = null

) : BindableItem<ViewDataBinding>(), Serializable {
    lateinit var binding: ItemCraneInfoSubquestionsBinding

    override fun getLayout(): Int {
        return R.layout.item_crane_info_subquestions
    }

    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        when (viewBinding) {
            is ItemCraneInfoSubquestionsBinding -> {
                viewBinding.data = this
                binding = viewBinding
                binding.answerEditText.doOnTextChanged { text, _, _, _ ->
                    answer = text.toString()
                }

            }

        }
    }

}

