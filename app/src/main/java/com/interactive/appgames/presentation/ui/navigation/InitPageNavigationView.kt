package com.interactive.appgames.presentation.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.interactive.appgames.presentation.ui.home.MainViewModel
import com.interactive.appgames.presentation.ui.common.GetTabsView
import com.interactive.appgames.presentation.ui.tabs.TabView

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 */

@Composable
fun InitPageView(mainViewModel: MainViewModel) {

    /*//Tabs
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
    )*/

    //lista de tabs que contendra el NavigationBarItem
    var tabBarItem = GetTabsView()

    //creacion de navController
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { TabView(tabBarItem, navController) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            //creacion de NavHost, seteando navController y estableciendo el tab inicial por medio de su titulo
            NavHost(navController = navController, startDestination = tabBarItem[0].title) {
                composable(tabBarItem[0].title) {
                    //Logica para pantalla de inicio
                    HomePageNavigationView(mainViewModel)
                }
                composable(tabBarItem[1].title) {
                    //Logica para pantalla de favoritos
                }
            }
        }
    }
}