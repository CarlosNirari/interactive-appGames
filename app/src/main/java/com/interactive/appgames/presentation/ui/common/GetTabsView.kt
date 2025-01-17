package com.interactive.appgames.presentation.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.interactive.appgames.R
import com.interactive.appgames.data.model.TabBarItem

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
 * Descripcion: Este Composable permite la administracion de los tabs para una gestion mas limpia
 */
@Composable
fun GetTabsView(): List<TabBarItem> {
    return listOf(
        //Tabs
        TabBarItem(
            title = stringResource(id = R.string.tab_home),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        TabBarItem(
            title = stringResource(id = R.string.tab_manual),
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            favoriteAmount = null
        )
    )
}
