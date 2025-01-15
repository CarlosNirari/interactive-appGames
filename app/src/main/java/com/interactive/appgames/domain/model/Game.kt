package com.interactive.appgames.domain.model

import com.interactive.appgames.data.database.GameEntity
import com.interactive.appgames.data.model.GameDTO

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
data class Game(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val short_description: String,
    val game_url: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val release_date: String,
    val freetogame_profile_url: String
)

fun GameDTO.toDomain() = Game(
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

fun GameEntity.toDomain() = Game(
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