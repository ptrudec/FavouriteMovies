package com.android.favouritemovies.presentation.util.route

/**
 * Created by petar.tomorad-rudec on 19/01/2024
 */

sealed class AppScreen(val route: String) {
    object HomeScreen : AppScreen(ConstantAppScreenName.HOME_SCREEN)
    object FavoritesScreen : AppScreen(ConstantAppScreenName.FAVORITES_SCREEN)
}


object ConstantAppScreenName {
    const val HOME_SCREEN = "home_screen"
    const val FAVORITES_SCREEN = "favorites_screen"
}