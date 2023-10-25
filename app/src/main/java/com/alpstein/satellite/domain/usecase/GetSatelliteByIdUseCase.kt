package com.alpstein.satellite.domain.usecase

import com.alpstein.satellite.domain.entity.Satellite
import com.alpstein.satellite.domain.repository.local.LocalRepository
import kotlinx.coroutines.flow.catch

class GetSatelliteByIdUseCase(private val localRepository: LocalRepository) {
    suspend operator fun invoke(
        id: Int,
        onResult: suspend (Satellite) -> Unit
    ){
        return localRepository.getItemByItem(id)
            .catch {
                println(it.localizedMessage)
            }
            .collect{
                onResult(it)
            }
    }
}