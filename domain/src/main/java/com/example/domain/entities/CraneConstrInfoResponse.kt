package com.example.domain.entities

data class CraneConstrInfo(
    val list: List<CraneConstrPartInfo>
)

data class CraneConstrPartInfo(
    val name: String,
    val pieces: List<CraneConstrPiecesInfo>
)

data class CraneConstrPiecesInfo(
    val name: String
)