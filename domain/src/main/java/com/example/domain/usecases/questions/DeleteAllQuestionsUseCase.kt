package com.example.domain.usecases.questions

import com.example.domain.repositories.QuestionRepository
import com.example.domain.usecases.BaseUseCase

class DeleteAllQuestionsUseCase(
    private val questionRepository: QuestionRepository
) : BaseUseCase<Unit, Unit>() {

    override suspend fun run(params: Unit) {
        questionRepository.deleteAllQuestions()
    }

}