package com.example.crane.ui.crane_info

import android.content.Context
import androidx.lifecycle.*

import com.example.ui_components.events.Event
import com.example.crane.ui.items.CraneInfoSubQuestionsUi
import com.example.crane.ui.items.CraneInfoUi
import com.example.crane.utils.getCraneConstrInfoResponseFromAssetFile
import com.example.crane.utils.getCraneElInfoResponseFromAssetFile
import com.example.crane.utils.getCraneInfoResponseFromAssetFile
import com.example.crane.utils.getCraneMechInfoResponseFromAssetFile
import com.example.domain.entities.CraneInfo
import com.example.domain.entities.CraneInfoResponse
import com.example.domain.entities.CraneInfoSubQuestions
import com.example.domain.usecases.questions.CreateQuestionUseCase
import com.example.domain.usecases.questions.DeleteAllQuestionsUseCase
import com.example.domain.usecases.questions.GetAllQuestionsUseCase
import com.example.ui_components.base.BaseViewModel
import com.hadilq.liveevent.LiveEvent
import timber.log.Timber

class CraneInfoViewModel(
    val createQuestionUseCase: CreateQuestionUseCase,
    val getAllQuestionsUseCase: GetAllQuestionsUseCase,
    val deleteAllQuestionsUseCase: DeleteAllQuestionsUseCase
) : BaseViewModel() {
    private val _items = MutableLiveData<List<CraneInfoUi>>()
    val items: LiveData<List<CraneInfoUi>> = _items

    val saveEvent = LiveEvent<Event<Boolean>>()
    val deleteEvent = LiveEvent<Event<Boolean>>()
    val newDestination = LiveEvent<Event<Boolean>>()

    fun requestItems(
        context: Context
    ) {
        Timber.i("requestItems")
        getAllQuestionsUseCase(viewModelScope, Unit) {
            val list = transformDataToCraneInfoUi(CraneInfoResponse(it))
            Timber.i("Size is ${list.size}")
            if (list.isNotEmpty()) {
                _items.value = list
            } else
                getDataFromAssets(context)
        }
    }

    private fun getDataFromAssets(context: Context) {
        Timber.i("getDataFromAssets")
        val response = getCraneInfoResponseFromAssetFile(context)
        response.list.forEachIndexed { index, question ->
            createQuestionUseCase(viewModelScope, question) {
                if (index == response.list.lastIndex)
                    requestItems(context)
            }
        }
    }

    fun saveData() {
        Timber.i("saveData")
        deleteAllQuestionsUseCase(viewModelScope, Unit) {
            _items.value?.forEachIndexed { index, question ->
                createQuestionUseCase(viewModelScope, question.let {
                    CraneInfo(
                        required = it.required,
                        question = it.question,
                        answer = it.answer,
                        subQuestions = it.subQuestions.map { subQuestion ->
                            CraneInfoSubQuestions(
                                question = subQuestion.question,
                                answer = subQuestion.answer
                            )
                        }
                    )
                })
                if (index == _items.value?.lastIndex)
                    saveEvent.postValue(Event(true))
            }
        }
    }

    fun deleteData(context: Context) {
        Timber.i("deleteData")
        deleteAllQuestionsUseCase(viewModelScope, Unit) {
            val response = getCraneInfoResponseFromAssetFile(context)
            response.list.forEachIndexed { index, question ->
                Timber.i("position $index")
                createQuestionUseCase(viewModelScope, question) {
                    if (index == response.list.lastIndex) {
                        requestItems(context)
                        deleteEvent.postValue(Event(true))
                    }
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

    fun checkOnCompleteness() {
        _items.value?.forEach {
            if (it.required) {
                if (it.answer.isNullOrEmpty() && it.subQuestions.isEmpty()) {
                    newDestination.value = Event(false)
                    return
                }
                it.subQuestions.forEach { subQuestion ->
                    if (subQuestion.answer.isNullOrEmpty()) {
                        newDestination.value = Event(false)
                        return
                    }
                }
            }
        }
        deleteAllQuestionsUseCase(viewModelScope, Unit) {
            _items.value?.forEachIndexed { index, it ->
                val item = it.let {
                    CraneInfo(
                        required = it.required,
                        question = it.question,
                        answer = it.answer,
                        subQuestions = it.subQuestions.map { sub ->
                            CraneInfoSubQuestions(
                                answer = sub.answer,
                                question = sub.question
                            )
                        }

                    )
                }
                createQuestionUseCase(viewModelScope, item) {
                    if (index == _items.value?.lastIndex) {
                        newDestination.value = Event(true)
                    }
                }
            }
        }

    }


}