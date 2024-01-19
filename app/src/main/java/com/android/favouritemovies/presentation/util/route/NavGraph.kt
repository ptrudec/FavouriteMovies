package com.android.favouritemovies.presentation.util.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.favouritemovies.presentation.favorites.FavoritesScreen
import com.android.favouritemovies.presentation.home.HomeScreen

/**
 * Created by petar.tomorad-rudec on 19/01/2024
 */

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.HomeScreen.route,
    ) {

        composable(route = AppScreen.HomeScreen.route) {
            HomeScreen(
                navController = navController
            )
        }

        composable(route = AppScreen.FavoritesScreen.route) {
            FavoritesScreen()
        }
    }
}
