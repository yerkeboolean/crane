package com.example.ui_components.custom_view

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.ui_components.R

class CustomToast(parent: ViewGroup) {
    private var title: TextView
    private var toast: Toast
    private var cardView: CardView

    init {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_custom_toast, parent, false)
        title = view.findViewById(R.id.title)
        cardView = view.findViewById(R.id.card_view)
        toast = Toast(context)
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0)
        toast.view = view
    }

    fun showMessage(text: String?) {
        title.text = text
        toast.show()
    }

    fun setBackground(color: Int): CustomToast {
        cardView.setCardBackgroundColor(color)
        return this
    }
}