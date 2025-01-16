package com.interactive.appgames

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 */
@HiltAndroidApp

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
