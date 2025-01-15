package com.interactive.appgames.domain.usecase

import com.interactive.appgames.common.Result
import com.interactive.appgames.data.api.ApiService
import com.interactive.appgames.domain.model.Game
import com.interactive.appgames.domain.repository.GameRepository
import javax.inject.Inject

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
class GetAllGamesApiUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke(): Result<List<Game>> {
        return try {
            val result = repository.getAllGameApi()
            when (result) {
                is Result.Success -> {
                    if (result.data.isNotEmpty()) {
                        repository.insertGames(result.data)
                    }
                    Result.Success(result.data)
                }

                is Result.Error -> {
                    Result.Error(result.exception)
                }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}