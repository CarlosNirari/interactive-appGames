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
    suspend fun insertGames(games: List<Game>): Result<String>
    suspend fun insertGame(games: Game): Result<String>
    suspend fun updateGame(game: Game): Result<String>
    suspend fun deleteGame(game: Game): Result<String>
    suspend fun getGamesByRanges(idLast: Int): Result<List<Game>>
    suspend fun getGamesByFilter(query: String): Result<List<Game>>
    suspend fun getGameById(id: Int): Result<Game>
    suspend fun getAllGameApi(): Result<List<Game>>
}