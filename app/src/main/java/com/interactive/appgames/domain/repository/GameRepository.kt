package com.interactive.appgames.domain.repository

import com.interactive.appgames.common.Result
import com.interactive.appgames.domain.model.Game
import kotlinx.coroutines.flow.Flow

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
interface GameRepository {
    suspend fun insertGames(games: List<Game>)
    suspend fun updateGame(game: Game)
    suspend fun deleteGame(game: Game)
    suspend fun getAllGames(): Result<Flow<List<Game>>>
    suspend fun getGamesByFilter(query: String): Result<Flow<List<Game>>>
    suspend fun getGameById(id: Int): Result<Flow<Game>>
    suspend fun getAllGameApi(): Result<List<Game>>
}