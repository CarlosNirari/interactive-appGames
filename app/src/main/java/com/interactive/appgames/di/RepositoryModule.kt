package com.interactive.appgames.di

import com.interactive.appgames.data.repository.GameRepositoryImpl
import com.interactive.appgames.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideGameRepository(gameRepositoryImpl: GameRepositoryImpl): GameRepository {
        return gameRepositoryImpl
    }
}