package com.example.domain.usecases.questions

import com.example.domain.entities.CraneInfo
import com.example.domain.repositories.QuestionRepository
import com.example.domain.usecases.BaseUseCase

class CreateQuestionUseCase(
    private val questionRepository: QuestionRepository
) : BaseUseCase<Unit, CraneInfo>() {

    override suspend fun run(params: CraneInfo) {
        questionRepository.insertQuestion(params)
    }

}