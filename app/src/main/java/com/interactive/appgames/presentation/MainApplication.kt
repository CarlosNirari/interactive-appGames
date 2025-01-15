package com.interactive.appgames.presentation

import android.app.Application
import android.content.Context

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        init(applicationContext)
    }

    companion object {
        var CONTEXT: Context? = null

        @JvmStatic
        fun init(
            context: Context?
        ) {
            CONTEXT = context;
        }
    }
}