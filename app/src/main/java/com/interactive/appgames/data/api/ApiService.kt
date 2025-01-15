package com.interactive.appgames.data.api

import com.interactive.appgames.data.model.GameDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
class ApiService @Inject constructor(private val apiClient: ApiClient) {
    suspend fun getAllGameApi(): List<GameDTO> {
        /*Implementacion de Corrutina, permite ejecutar en un hilo los procesos de peticion al repositorio*/
        return withContext(Dispatchers.IO) {
            val respose = apiClient.getAllGameApi()
            respose.body() ?: emptyList()
        }

    }
}