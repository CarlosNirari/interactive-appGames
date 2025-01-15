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
    val id: Int = 0,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("thumbnail")
    val thumbnail: String,
    @ColumnInfo("short_description")
    val short_description: String,
    @ColumnInfo("game_url")
    val game_url: String,
    @ColumnInfo("genre")
    val genre: String,
    @ColumnInfo("platform")
    val platform: String,
    @ColumnInfo("publisher")
    val publisher: String,
    @ColumnInfo("developer")
    val developer: String,
    @ColumnInfo("release_date")
    val release_date: String,
    @ColumnInfo("freetogame_profile_url")
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
