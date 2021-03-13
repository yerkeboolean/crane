package com.example.domain.entities


data class CranePartInfoResponse(
    val list: List<CranePartInfo>
)

data class CranePartInfo(
    val id: Int,
    val type: String,
    val craneParts: List<CraneParts>
)

data class CraneParts(
    val name: String,
    val filled: Boolean? = null,
    val openView: Boolean? = null,
    val pieces: List<CranePartsPieces>
)

data class CranePartsPieces(
    val name: String,
    val comment: String? = null,
    val filled: Boolean? = null,
    val satisfactory: Boolean? = null,
    val unsatisfactory: Boolean? = null
)