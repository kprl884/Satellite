package com.alpstein.satellite.domain.usecase

import com.alpstein.satellite.domain.entity.Satellite
import com.alpstein.satellite.domain.repository.local.LocalRepository
import kotlinx.coroutines.flow.catch

class ListGetLocalUseCase(private val localRepository: LocalRepository) {
    suspend operator fun invoke(
        onResult: suspend (List<Satellite>) -> Unit
    ){
        return localRepository.getAllItems()
            .catch {
               println(it.localizedMessage)
            }
            .collect{
                onResult(it)
            }
    }
}