package com.interactive.appgames.domain.usecase

import com.interactive.appgames.common.Constans
import com.interactive.appgames.common.Result
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
            val apiResult = repository.getAllGameApi()
            when (apiResult) {
                is Result.Success -> {
                    if (apiResult.data.isNotEmpty()) {
                        val dbInsertResult = repository.insertGames(apiResult.data)
                        if (dbInsertResult is Result.Error) {
                            Result.Error(dbInsertResult.exception)
                        }
                    }

                    val dbResult = repository.getGamesByRanges(Constans.INT_DEFAULT)
                    when (dbResult) {
                        is Result.Success -> dbResult
                        is Result.Error -> Result.Error(dbResult.exception)
                    }
                }

                is Result.Error -> {
                    Result.Error(apiResult.exception)
                }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}