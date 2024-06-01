package fr.epf.min1.aroundtheworld2.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.epf.min1.aroundtheworld2.screens.PlayScreen
import fr.epf.min1.aroundtheworld2.screens.SearchScreen
import fr.epf.min1.aroundtheworld2.screens.FavoritesScreen
import fr.epf.min1.aroundtheworld2.screens.Screen
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
@Composable
fun Navigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Play.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Screen.Play.route) { PlayScreen() }
        composable(Screen.Search.route) { SearchScreen() }
        composable(Screen.Favorites.route) { FavoritesScreen() }
    }
}
