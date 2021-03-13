package com.example.domain.usecases.crane_parts

import com.example.domain.entities.CranePartInfo
import com.example.domain.repositories.CranePartRepository
import com.example.domain.usecases.BaseUseCase

class GetCraneByIdUseCase(
    private val cranePartRepository: CranePartRepository
) : BaseUseCase<List<CranePartInfo>, Int>() {

    override suspend fun run(params: Int): List<CranePartInfo> {
        return cranePartRepository.getCraneById(params)
    }

}