package com.interactive.appgames.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
@Entity(tableName = "games_table")
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
    val freetogame_profile_url: String)
