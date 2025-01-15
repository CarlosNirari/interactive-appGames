package com.interactive.appgames.domain.usecase

import com.interactive.appgames.domain.model.Game
import com.interactive.appgames.domain.repository.GameRepository
import javax.inject.Inject

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
class InsertGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend fun invoke(games: List<Game>) {
        repository.insertGames(games)
    }
}