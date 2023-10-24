package com.alpstein.satellite.domain.repository.local

import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun getAllItems() : Flow<Int>
}