package com.interactive.appgames.data.api

import com.interactive.appgames.data.model.GameDTO
import retrofit2.Response
import retrofit2.http.GET

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
interface ApiClient {
    @GET("/api/games")
    suspend fun getAllGameApi(): Response<List<GameDTO>>
}