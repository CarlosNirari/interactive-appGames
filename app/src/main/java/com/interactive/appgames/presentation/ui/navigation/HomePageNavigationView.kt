package com.interactive.appgames.presentation.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.interactive.appgames.presentation.ui.home.MainViewModel
import com.interactive.appgames.presentation.ui.detail.HomePageDetailView
import com.interactive.appgames.presentation.ui.detail.HomePageView

/**
 * Creado por: charssanti
 * Proyecto: appGames
 * Fecha de Creacion: 14/01/2025
 */

@Composable
fun HomePageNavigationView(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    //creacion de NavHost, seteando navController y estableciendo el tab inicial por medio de su titulo
    NavHost(navController = navController, startDestination = Screen.HomeScreen.name) {
        composable(route = Screen.HomeScreen.name) {
            //logica de la vista HomePage
            HomePageView(
                mainViewModel = mainViewModel,
                onUpdate = { id ->
                    navController.navigate(
                        route = "${Screen.DetailScreen.name} / $id"
                    )
                }
            )
        }

        composable(
            route = "${Screen.DetailScreen.name} / {id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(300)
                )
            }
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id").let { id ->
                HomePageDetailView(
                    id = id!!,
                    mainViewModel = mainViewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }

    }
}