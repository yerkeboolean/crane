package com.example.data.repositories

import com.example.data.daos.QuestionDao
import com.example.domain.entities.CraneInfo
import com.example.domain.entities.CraneInfoSubQuestions
import com.example.domain.repositories.QuestionRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DefaultQuestionRepository(
    private val questionDao: QuestionDao
) : QuestionRepository {
    override suspend fun insertQuestion(question: CraneInfo) {
        val item = question.let {
            com.example.data.entities.CraneInfo(
                required = it.required,
                question = it.question,
                answer = it.answer,
                subQuestions = it.subQuestions.map { sub ->
                    com.example.data.entities.CraneInfoSubQuestions(
                        answer = sub.answer,
                        question = sub.question
                    )
                }
            )
        }
        GlobalScope.launch {
            questionDao.insert(item)
        }.join()
    }

    override suspend fun getAllQuestions(): List<CraneInfo> {
        var list: List<CraneInfo> = listOf()
        GlobalScope.launch {
            list = questionDao.getAllQuestions().map {
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
        }.join()

        return list
    }

    override suspend fun deleteAllQuestions() {
        GlobalScope.launch {
            questionDao.deleteAllQuestions()
        }.join()
    }

}