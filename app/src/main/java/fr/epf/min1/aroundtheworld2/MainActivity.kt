package fr.epf.min1.aroundtheworld2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.epf.min1.aroundtheworld2.ui.theme.MyTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    Navigation(navController, innerPadding)
                }
            }
        }
    }
}

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

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(Screen.Play, Screen.Search, Screen.Favorites)
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                label = { Text(text = item.title) },
                selected = item.route == navController.currentBackStackEntry?.destination?.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Play : Screen("play", "Play", Icons.Default.PlayArrow)
    object Search : Screen("search", "Search", Icons.Default.Search)
    object Favorites : Screen("favorites", "Favorites", Icons.Default.Favorite)
}

@Composable
fun PlayScreen() {
    Text(text = "Play Screen")
}

@Composable
fun SearchScreen() {
    Text(text = "Search Screen")
}

@Composable
fun FavoritesScreen() {
    Text(text = "Favorites Screen")
}
