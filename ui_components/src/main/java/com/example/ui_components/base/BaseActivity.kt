package com.example.ui_components.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.ui_components.R

abstract class BaseActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(baseContext, R.color.black)
    }

    fun setStatusBar(color: Int) {
        window.statusBarColor = ContextCompat.getColor(baseContext, color)
    }

}