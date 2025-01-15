package com.interactive.appgames.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.interactive.appgames.R
import com.interactive.appgames.model.TabBarItem
import com.interactive.appgames.presentation.view.navigation.HomePageNavigationView
import com.interactive.appgames.presentation.view.tabs.TabView

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 */

@Composable
fun InitPageView(mainViewModel: MainViewModel) {

    //Tabs
    val homeTab = TabBarItem(
        title = stringResource(id = R.string.tab_home),
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )
    val favoriteTab = TabBarItem(
        title = stringResource(id = R.string.tab_favorite),
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        favoriteAmount = null

    )

    //lista de tabs que contendra el NavigationBarItem
    var tabBarItem = listOf(homeTab, favoriteTab)

    //creacion de navController
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { TabView(tabBarItem, navController) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            //creacion de NavHost, seteando navController y estableciendo el tab inicial por medio de su titulo
            NavHost(navController = navController, startDestination = homeTab.title) {
                composable(homeTab.title) {
                    //Logica para pantalla de inicio
                    HomePageNavigationView(mainViewModel)
                }
                composable(favoriteTab.title) {
                    //Logica para pantalla de favoritos
                }
            }
        }
    }
}