package com.interactive.appgames.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.interactive.appgames.data.model.GameDTO
import com.interactive.appgames.domain.model.Game

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
@Entity(tableName = "game_table")
data class GameEntity(
    @PrimaryKey
    val id: Int ,
    @ColumnInfo(name ="title")
    val title: String,
    @ColumnInfo(name ="thumbnail")
    val thumbnail: String,
    @ColumnInfo(name ="short_description")
    val short_description: String,
    @ColumnInfo(name ="game_url")
    val game_url: String,
    @ColumnInfo(name ="genre")
    val genre: String,
    @ColumnInfo(name ="platform")
    val platform: String,
    @ColumnInfo(name ="publisher")
    val publisher: String,
    @ColumnInfo(name ="developer")
    val developer: String,
    @ColumnInfo(name ="release_date")
    val release_date: String,
    @ColumnInfo(name ="freetogame_profile_url")
    val freetogame_profile_url: String
)

fun Game.toEntity() = GameEntity(
    id = id,
    title = title,
    thumbnail = thumbnail,
    short_description = short_description,
    game_url = game_url,
    genre = genre,
    platform = platform,
    publisher = publisher,
    developer = developer,
    release_date = release_date,
    freetogame_profile_url = freetogame_profile_url
)
