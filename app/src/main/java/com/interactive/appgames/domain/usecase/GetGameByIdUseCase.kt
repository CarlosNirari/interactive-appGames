package com.interactive.appgames.domain.usecase

import com.interactive.appgames.common.Result
import com.interactive.appgames.domain.model.Game
import com.interactive.appgames.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
class GetGameByIdUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend fun invoke(id: Int): Result<Flow<Game>> {
        return repository.getGameById(id)
    }
}