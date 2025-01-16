package com.interactive.appgames.data.repository

import com.interactive.appgames.common.Result
import com.interactive.appgames.data.api.ApiService
import com.interactive.appgames.data.database.GameDao
import com.interactive.appgames.data.database.toEntity
import com.interactive.appgames.domain.model.Game
import com.interactive.appgames.domain.model.toDomain
import com.interactive.appgames.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
class GameRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val gameDao: GameDao
) : GameRepository {

    override suspend fun insertGames(games: List<Game>) {
        gameDao.insertGames(games.map { it.toEntity() })
    }

    override suspend fun updateGame(game: Game) {
        gameDao.updateGame(game.toEntity())
    }

    override suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game.toEntity())
    }

    override suspend fun getAllGames(): Result<List<Game>> {
        return try {
            //mapeo para conversion de Entidad DAO a Entidad de Dominio sin perder la implementacion de flow
            Result.Success(gameDao.getAllGames().map { it.toDomain() })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getGamesByRanges(idLast: Int): Result<List<Game>> {
        return try {
            //mapeo para conversion de Entidad DAO a Entidad de Dominio sin perder la implementacion de flow
            Result.Success(gameDao.getGamesByRanges(idLast).map { it.toDomain() })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getGamesByFilter(query: String): Result<List<Game>> {
        return try {
            //mapeo para conversion de Entidad DAO a Entidad de Dominio sin perder la implementacion de flow
            Result.Success(gameDao.getGamesByFilter(query).map {
                it.toDomain()
            })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getGameById(id: Int): Result<Game> {
        return try {
            //mapeo para conversion de Entidad DAO a Entidad de Dominio sin perder la implementacion de flow
            val game = gameDao.getGameById(id)
            Result.Success(
                Game(
                    id = game.id,
                    title = game.title,
                    thumbnail = game.thumbnail,
                    short_description = game.short_description,
                    game_url = game.game_url,
                    genre = game.genre,
                    platform = game.platform,
                    publisher = game.publisher,
                    developer = game.developer,
                    release_date = game.release_date,
                    freetogame_profile_url = game.freetogame_profile_url
                )
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getAllGameApi(): Result<List<Game>> {
        return try {
            //mapeo para conversion de Entidad API a Entidad de Dominio
            Result.Success(apiService.getAllGameApi().map {
                it.toDomain()
            })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}