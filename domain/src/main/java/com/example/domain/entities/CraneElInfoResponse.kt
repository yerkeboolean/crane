package com.example.domain.entities

data class CraneElInfoResponse(
    val list: List<CraneElInfo>
)

data class CraneElInfo(
    val id: Int,
    val craneParts: List<CraneElPartInfo>
)

data class CraneElPartInfo(
    val name: String,
    val pieces: List<CraneElPartPiecesInfo>
)

data class CraneElPartPiecesInfo(
    val name: String
)