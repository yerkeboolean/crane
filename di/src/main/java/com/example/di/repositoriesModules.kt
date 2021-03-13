package com.example.di

import com.example.data.repositories.DefaultCranePartRepository
import com.example.data.repositories.DefaultQuestionRepository
import com.example.domain.repositories.CranePartRepository
import com.example.domain.repositories.QuestionRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single<QuestionRepository> { DefaultQuestionRepository(questionDao = get()) }
    single<CranePartRepository> { DefaultCranePartRepository(cranePartDao = get()) }

}