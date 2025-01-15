package com.interactive.appgames.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 */
data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val favoriteAmount: Int? = null
)
