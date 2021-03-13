package com.example.domain.usecases.questions

import com.example.domain.entities.CraneInfo
import com.example.domain.repositories.QuestionRepository
import com.example.domain.usecases.BaseUseCase

class GetAllQuestionsUseCase(
    private val questionRepository: QuestionRepository
) : BaseUseCase<List<CraneInfo>, Unit>() {

    override suspend fun run(params: Unit): List<CraneInfo> {
        return questionRepository.getAllQuestions()
    }

}