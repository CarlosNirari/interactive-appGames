package com.interactive.appgames.presentation.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.interactive.appgames.R
import com.interactive.appgames.data.model.TabBarItem

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 15/01/2025
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
            title = stringResource(id = R.string.tab_favorite),
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            favoriteAmount = null
        )
    )
}
