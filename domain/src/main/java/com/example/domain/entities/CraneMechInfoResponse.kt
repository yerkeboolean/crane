package com.example.domain.entities

data class CraneMechInfoResponse(
    val list: List<CraneMechInfo>
)

data class CraneMechInfo(
    val id: Int,
    val craneParts: List<CraneMechPartInfo>
)

data class CraneMechPartInfo(
    val name: String,
    val pieces: List<CraneMechPartPiecesInfo>
)

data class CraneMechPartPiecesInfo(
    val name: String
)