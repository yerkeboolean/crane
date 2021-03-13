package com.example.crane.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crane.ui.items.CranePartPiecesUi
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

@BindingAdapter("setCranePartsPiecesUi")
fun RecyclerView.setCranePartsPiecesUi(data: List<CranePartPiecesUi>) {
    val adapter = GroupAdapter<GroupieViewHolder>()
    adapter.addAll(data)
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    this.adapter = adapter
    this.adapter?.notifyDataSetChanged()
}