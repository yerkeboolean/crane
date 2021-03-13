package com.example.crane.ui.crane_metal_constructor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crane.ui.items.CranePartPiecesUi
import com.example.crane.ui.items.CranePartsUi
import com.example.domain.entities.CranePartInfo
import com.example.domain.entities.CraneParts
import com.example.domain.entities.CranePartsPieces
import com.example.domain.usecases.crane_parts.GetCraneByIdUseCase
import com.example.domain.usecases.crane_parts.UpdateCraneByIdAndTypeUseCase
import com.example.ui_components.events.Event
import com.hadilq.liveevent.LiveEvent
import timber.log.Timber

class CraneMetalConstructorInfoViewModel(
    private val getCraneByIdUseCase: GetCraneByIdUseCase,
    private val updateCraneByIdAndTypeUseCase: UpdateCraneByIdAndTypeUseCase

) : ViewModel() {
    val hideKeyboardEvent = LiveEvent<Event<Boolean>>()
    val saveEvent = LiveEvent<Event<Boolean>>()

    private val _itemsConstr = MutableLiveData<List<CranePartsUi>>()
    val itemsConstr: LiveData<List<CranePartsUi>> = _itemsConstr

    var id = 1

    private val hideKeyboardAction: () -> Unit = {
        hideKeyboardEvent.postValue(Event(true))
    }
    private val actionToCheckConstrPieces: (() -> Unit) = {
        _itemsConstr.value?.forEach {
            var temp = false
            it.pieces.forEach {
                if (it.value.filled == null || it.value.filled == false)
                    temp = true
            }
            it.value.filled = temp
        }
    }

    fun requestItems(
        id: Int
    ) {
        Timber.i("requestItems")
        this.id = id
        getCraneByIdUseCase(viewModelScope, id) {
            it.forEach { cranePartInfo ->
                when (cranePartInfo.type) {
                    "Constr" -> {
                        _itemsConstr.value = transformDataToCranePartsUi(cranePartInfo)
                    }
                }
            }
        }
    }

    private fun transformDataToCranePartsUi(response: CranePartInfo): List<CranePartsUi> {
        return response.craneParts.let {
            val list: MutableList<CranePartsUi> = mutableListOf()
            it.forEach {
                val item = CranePartsUi(
                    name = it.name,
                    pieces = it.pieces.let {
                        val listOfPieces: MutableList<CranePartPiecesUi> = mutableListOf()
                        it.forEach { pieces ->
                            val item = CranePartPiecesUi(
                                pieces.name,
                                comment = pieces.comment,
                                actionToCheck = actionToCheckConstrPieces,
                                hideKeyBoard = hideKeyboardAction
                            )
                            item.value.satisfactory = pieces.satisfactory
                            item.value.unsatisfactory = pieces.unsatisfactory
                            item.value.filled = pieces.filled

                            listOfPieces.add(item)
                        }
                        listOfPieces
                    }
                )

                it.filled?.let { item.value.filled = it }
                item.value.openView = it.openView

                list.add(item)
            }
            list
        }
    }

    fun checkOnCompleteness(): Boolean {
        _itemsConstr.value?.forEach {
            if (it.value.filled) {
                return false
            }
        }
        return true
    }
    fun saveData() {
        updateCraneByIdAndTypeUseCase(
            viewModelScope, UpdateCraneByIdAndTypeUseCase.Params(
                id, "Constr",
                _itemsConstr.value?.map {
                    CraneParts(
                        name = it.name,
                        filled = it.value.filled,
                        openView = it.value.openView,
                        pieces = it.pieces.map { pieces ->
                            CranePartsPieces(
                                comment = pieces.comment,
                                filled = pieces.value.filled,
                                name = pieces.name,
                                satisfactory = pieces.value.satisfactory,
                                unsatisfactory = pieces.value.unsatisfactory
                            )
                        }

                    )
                }!!
            )
        ){
            saveEvent.postValue(Event(true))
        }

    }

}
