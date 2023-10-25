package com.alpstein.satellite.domain.repository.local

import com.alpstein.satellite.domain.entity.Satellite
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun getAllItems() : Flow<List<Satellite>>

    suspend fun getItemByItem(id: Int): Flow<Satellite>
}