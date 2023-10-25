package com.alpstein.satellite.domain.di

import com.alpstein.satellite.domain.repository.local.LocalRepository
import com.alpstein.satellite.domain.usecase.GetSatelliteByIdUseCase
import com.alpstein.satellite.domain.usecase.ListGetLocalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @ViewModelScoped
    @Provides
    fun provideGetShoppingListLocalUseCase(
        localRepository: LocalRepository
    ): ListGetLocalUseCase {
        return ListGetLocalUseCase(
            localRepository
        )
    }

    @ViewModelScoped
    @Provides
    fun provideGetSatelliteItemByIdLocalUseCase(
        shoppingLocalRepository: LocalRepository
    ): GetSatelliteByIdUseCase {
        return GetSatelliteByIdUseCase(
            shoppingLocalRepository
        )
    }
}