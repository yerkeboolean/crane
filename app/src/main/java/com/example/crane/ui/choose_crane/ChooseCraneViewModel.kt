package com.example.crane.ui.choose_crane

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crane.R
import com.example.crane.ui.items.ChooseCraneTypeUi

class ChooseCraneViewModel : ViewModel() {
    private val _items = MutableLiveData<List<ChooseCraneTypeUi>>()
    val items: LiveData<List<ChooseCraneTypeUi>> = _items

    fun requestItems() {
        val list: MutableList<ChooseCraneTypeUi> = mutableListOf()
        list.add(ChooseCraneTypeUi(1, R.drawable.crane_balka, "Кран балка"))
        _items.value = list
    }

}