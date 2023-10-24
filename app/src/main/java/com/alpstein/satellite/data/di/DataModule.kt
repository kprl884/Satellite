package com.alpstein.satellite.data.di

import android.content.Context
import com.alpstein.satellite.domain.repository.local.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideAnalyticsService(
        context: Context
    ): LocalRepository {
        return
    }
}