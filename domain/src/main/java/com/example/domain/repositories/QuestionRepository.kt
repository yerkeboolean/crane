package com.example.domain.repositories

import com.example.domain.entities.CraneInfo


interface QuestionRepository {
    suspend fun insertQuestion(question: CraneInfo)
    suspend fun getAllQuestions():List<CraneInfo>
    suspend fun deleteAllQuestions()
}