package com.interactive.appgames.data.model

import com.google.gson.annotations.SerializedName
import com.interactive.appgames.domain.model.Game

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
data class GameDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("short_description") val short_description: String,
    @SerializedName("game_url") val game_url: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("platform") val platform: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("developer") val developer: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("freetogame_profile_url") val freetogame_profile_url: String
)

