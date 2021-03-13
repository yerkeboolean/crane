package com.example.di

import com.example.domain.usecases.crane_parts.*
import com.example.domain.usecases.questions.CreateQuestionUseCase
import com.example.domain.usecases.questions.DeleteAllQuestionsUseCase
import com.example.domain.usecases.questions.GetAllQuestionsUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { CreateQuestionUseCase(questionRepository = get()) }
    factory { DeleteAllQuestionsUseCase(questionRepository = get()) }
    factory { GetAllQuestionsUseCase(questionRepository = get()) }

    factory { CreateCranePartUseCase(cranePartRepository = get()) }
    factory { DeleteAllCranePartsUseCase(cranePartRepository = get()) }
    factory { GetAllCranePartsUseCase(cranePartRepository = get()) }
    factory { GetCraneByIdUseCase(cranePartRepository = get()) }
    factory { UpdateCraneByIdAndTypeUseCase(cranePartRepository = get()) }

}