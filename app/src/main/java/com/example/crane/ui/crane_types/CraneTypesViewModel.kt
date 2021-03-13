package com.example.crane.ui.crane_types

import android.content.Context
import androidx.lifecycle.*
import com.example.crane.ui.items.*
import com.example.crane.utils.getCraneConstrInfoResponseFromAssetFile
import com.example.crane.utils.getCraneElInfoResponseFromAssetFile
import com.example.crane.utils.getCraneMechInfoResponseFromAssetFile
import com.example.domain.entities.*
import com.example.domain.usecases.crane_parts.CreateCranePartUseCase
import com.example.domain.usecases.crane_parts.DeleteAllCranePartsUseCase
import com.example.domain.usecases.crane_parts.GetAllCranePartsUseCase
import com.example.domain.usecases.questions.DeleteAllQuestionsUseCase
import com.example.domain.usecases.questions.GetAllQuestionsUseCase
import timber.log.Timber

class CraneTypesViewModel(
    private val createCranePartUseCase: CreateCranePartUseCase,
    private val getAllCranePartsUseCase: GetAllCranePartsUseCase,
    private val deleteAllCranePartsUseCase: DeleteAllCranePartsUseCase,
    private val deleteAllQuestionsUseCase: DeleteAllQuestionsUseCase,
    private val getAllQuestionsUseCase: GetAllQuestionsUseCase
) : ViewModel() {
    private val _items = MutableLiveData<List<CraneTypeUi>>()
    val items: LiveData<List<CraneTypeUi>> = _items

    private val _сraneInfo = MutableLiveData<List<CraneInfoUi>>()
    val сraneInfo: LiveData<List<CraneInfoUi>> = _сraneInfo


    fun requestItems(context: Context) {


        val list: MutableList<CraneTypeUi> = mutableListOf()

        list.add(CraneTypeUi(1, name = "Механизм передвижения крана"))
        list.add(CraneTypeUi(2, name = "Механизм передвижения тали"))
        list.add(CraneTypeUi(3, name = "Механизм подъема"))
        list.add(CraneTypeUi(4, name = "Металлоконструкций"))
        getAllQuestionsUseCase(viewModelScope, Unit) {
            _сraneInfo.value = transformDataToCraneInfoUi(CraneInfoResponse(it))

            getAllCranePartsUseCase(viewModelScope, Unit) {
                if (it.isNotEmpty()) {
                    list.forEach { craneTypeUi ->
                        it.forEach { cranePartInfo ->
                            when (cranePartInfo.type) {
                                "Mech" -> {
                                    if (cranePartInfo.id == craneTypeUi.id) {
                                        craneTypeUi.value.cranePartsUiMech =
                                            cranePartInfo.craneParts.map { craneParts ->
                                                CranePartsUi(
                                                    name = craneParts.name,
                                                    pieces = craneParts.pieces.let {
                                                        val list: MutableList<CranePartPiecesUi> =
                                                            mutableListOf()
                                                        it.forEach { pieces ->
                                                            val item = CranePartPiecesUi(
                                                                pieces.name,
                                                                comment = pieces.comment
                                                            )
                                                            item.value.satisfactory =
                                                                pieces.satisfactory
                                                            item.value.unsatisfactory =
                                                                pieces.unsatisfactory
                                                            item.value.filled = pieces.filled
                                                            list.add(item)

                                                        }
                                                        list
                                                    }
                                                )
                                            }
                                    }
                                }
                                "Constr" -> {
                                    if (cranePartInfo.id == craneTypeUi.id) {
                                        craneTypeUi.value.cranePartsUiConstr =
                                            cranePartInfo.craneParts.map { craneParts ->
                                                CranePartsUi(
                                                    name = craneParts.name,
                                                    pieces = craneParts.pieces.let {
                                                        val list: MutableList<CranePartPiecesUi> =
                                                            mutableListOf()
                                                        it.forEach { pieces ->
                                                            val item = CranePartPiecesUi(
                                                                pieces.name,
                                                                comment = pieces.comment
                                                            )
                                                            item.value.satisfactory =
                                                                pieces.satisfactory
                                                            item.value.unsatisfactory =
                                                                pieces.unsatisfactory
                                                            item.value.filled = pieces.filled
                                                            list.add(item)

                                                        }
                                                        list
                                                    }
                                                )
                                            }
                                    }

                                }
                                "El" -> {
                                    if (cranePartInfo.id == craneTypeUi.id) {
                                        craneTypeUi.value.cranePartsUiEl =
                                            cranePartInfo.craneParts.map { craneParts ->
                                                CranePartsUi(
                                                    name = craneParts.name,
                                                    pieces = craneParts.pieces.let {
                                                        val list: MutableList<CranePartPiecesUi> =
                                                            mutableListOf()
                                                        it.forEach { pieces ->
                                                            val item = CranePartPiecesUi(
                                                                pieces.name,
                                                                comment = pieces.comment
                                                            )
                                                            item.value.satisfactory =
                                                                pieces.satisfactory
                                                            item.value.unsatisfactory =
                                                                pieces.unsatisfactory
                                                            item.value.filled = pieces.filled
                                                            list.add(item)
                                                        }
                                                        list
                                                    }
                                                )
                                            }
                                    }

                                }

                            }
                        }
                    }
                    _items.value = list

                } else {
                    setCranePartsData(context)
                }
            }
        }


    }

    private fun transformDataToCraneInfoUi(response: CraneInfoResponse): List<CraneInfoUi> {
        return response.list.map {
            CraneInfoUi(
                required = it.required,
                question = it.question,
                answer = it.answer,
                subQuestions = it.subQuestions.map { subQuestion ->
                    CraneInfoSubQuestionsUi(
                        question = subQuestion.question,
                        answer = subQuestion.answer
                    )
                }
            )
        }
    }

    fun checkOnCompleteness(): Boolean {
        _items.value?.forEach {
            if (!it.filled
            ) {
                return false
            }
        }
        return true
    }
    fun deleteAll(){
        deleteAllCranePartsUseCase(viewModelScope,Unit)
        deleteAllQuestionsUseCase(viewModelScope,Unit)
    }

    private fun setCranePartsData(context: Context) {
        deleteAllCranePartsUseCase(viewModelScope, Unit) {
            for (i in 1..4) {
                if (i != 4) {
                    val response1 = getCraneMechInfoResponseFromAssetFile(context, i)
                    val response2 = getCraneElInfoResponseFromAssetFile(context, i)
                    createCranePartUseCase(
                        viewModelScope,
                        transformToCranePartInfo(response1!!, i)
                    )
                    createCranePartUseCase(
                        viewModelScope,
                        transformToCranePartInfo(response2!!, i)
                    )
                } else {
                    val response3 = getCraneConstrInfoResponseFromAssetFile(context)
                    createCranePartUseCase(
                        viewModelScope,
                        transformToCranePartInfo(response3!!, i)
                    ) {
                        requestItems(context)
                    }
                }
            }
        }
    }

    private fun transformToCranePartInfo(data: CraneMechInfo, id: Int): CranePartInfo {
        return data.let {
            CranePartInfo(
                id = id,
                type = "Mech",
                craneParts = it.craneParts.map { craneMechPartInfo ->
                    CraneParts(
                        name = craneMechPartInfo.name,
                        pieces = craneMechPartInfo.pieces.map { craneMechPartPiecesInfo ->
                            CranePartsPieces(
                                craneMechPartPiecesInfo.name
                            )
                        }
                    )

                }
            )
        }


    }

    private fun transformToCranePartInfo(data: CraneElInfo, id: Int): CranePartInfo {
        return data.let {
            CranePartInfo(
                id = id,
                type = "El",
                craneParts = it.craneParts.map { craneMechPartInfo ->
                    CraneParts(
                        name = craneMechPartInfo.name,
                        pieces = craneMechPartInfo.pieces.map { craneMechPartPiecesInfo ->
                            CranePartsPieces(
                                craneMechPartPiecesInfo.name
                            )
                        }
                    )

                }
            )

        }

    }

    private fun transformToCranePartInfo(data: CraneConstrInfo, id: Int): CranePartInfo {
        return data.let {
            CranePartInfo(
                id = id,
                type = "Constr",
                craneParts = it.list.map { craneMechPartInfo ->
                    CraneParts(
                        name = craneMechPartInfo.name,
                        pieces = craneMechPartInfo.pieces.map { craneMechPartPiecesInfo ->
                            CranePartsPieces(
                                craneMechPartPiecesInfo.name
                            )
                        }
                    )
                }
            )

        }

    }
}