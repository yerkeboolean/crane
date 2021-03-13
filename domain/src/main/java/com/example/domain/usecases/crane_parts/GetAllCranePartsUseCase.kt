package com.example.domain.usecases.crane_parts

import com.example.domain.entities.CranePartInfo
import com.example.domain.repositories.CranePartRepository
import com.example.domain.usecases.BaseUseCase


class GetAllCranePartsUseCase(
    private val cranePartRepository: CranePartRepository
) : BaseUseCase<List<CranePartInfo>, Unit>() {

    override suspend fun run(params: Unit): List<CranePartInfo> {
        return cranePartRepository.getAllCraneParts()
    }

}