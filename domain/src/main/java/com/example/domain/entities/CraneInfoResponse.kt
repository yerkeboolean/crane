package com.example.domain.entities


data class CraneInfoResponse(
    val list: List<CraneInfo>

)

data class CraneInfo(
    val required: Boolean,
    val question: String,
    var answer: String? = null,
    val subQuestions: List<CraneInfoSubQuestions>

)

data class CraneInfoSubQuestions(
    val question: String,
    var answer: String? = null

)
