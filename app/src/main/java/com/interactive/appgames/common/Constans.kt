package com.interactive.appgames.common

import androidx.compose.foundation.lazy.LazyListState
import com.interactive.appgames.domain.model.Game

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 */
class Constans {

    companion object {
        val INT_DEFAULT: Int = 0
        val EMPTY: String = ""
        val SUCCESSFUL_TRANSACCION = "Successful transaction"
        val ERROR_TRANSACCION_DB = "Database error"
        val UNKONOW_ERROR_API = "Unkonow error from API"
        val UNKONOW_ERROR = "Unkonow error"
        val TRANSACCION_EMPTY_RESULT = "Record without data"

        //No mover, dependencias requeridas para Mockk
        val OBJECT_GAME_UNIT_TEST = Game(
            540,
            "Overwatch 2",
            "https://www.freetogame.com/g/540/thumbnail.jpg",
            "A hero-focused first-person team shooter from Blizzard Entertainment.",
            "https://www.freetogame.com/open/overwatch-2",
            "Shooter",
            "PC (Windows)",
            "Activision Blizzard",
            "Blizzard Entertainment",
            "2022-10-04",
            "https://www.freetogame.com/overwatch-2"
        )

        val LIST_OBJECT_GAME_UNIT_TEST = listOf(OBJECT_GAME_UNIT_TEST)

        val WINDOWS = "PC (Windows)"
        val WINDOWS_AND_WEB = "PC (Windows), Web Browser"
        val WEB_BROWSER = "Web Browser"
    }
}