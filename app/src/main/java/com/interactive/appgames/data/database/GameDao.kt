package com.interactive.appgames.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.interactive.appgames.data.model.GameDTO
import kotlinx.coroutines.flow.Flow

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(gameEntity: List<GameEntity>)

    @Update
    suspend fun updateGame(gameEntity: GameEntity)

    @Delete
    suspend fun deleteGame(gameEntity: GameEntity)

    @Query("SELECT * FROM game_table ORDER BY id DESC")
    suspend fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game_table WHERE (title like '%'||:query ||'%' OR genre like '%'||:query ||'%'OR id like '%'||:query ||'%')  ORDER BY id DESC")
    suspend fun getGamesByFilter(query: String): Flow<List<GameEntity>>

    @Query("SELECT * FROM game_table where id=:id")
    suspend fun getGameById(id:Int): Flow<GameEntity>
}
