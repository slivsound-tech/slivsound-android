package com.slivsound.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.slivsound.feature.discover.DiscoverScreen
import com.slivsound.feature.favorite.FavoriteScreen
import com.slivsound.feature.home.HomeScreen
import com.slivsound.feature.settings.SettingsScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = GRAPH_HOME,
        modifier = modifier
    ) {

        navigation(route = GRAPH_HOME, startDestination = ROUTE_HOME_MAIN) {
            composable(ROUTE_HOME_MAIN) { HomeScreen() }
        }

        navigation(route = GRAPH_DISCOVER, startDestination = ROUTE_DISCOVER_MAIN) {
            composable(ROUTE_DISCOVER_MAIN) { DiscoverScreen() }
        }

        navigation(route = GRAPH_FAVORITE, startDestination = ROUTE_FAVORITE_MAIN) {
            composable(ROUTE_FAVORITE_MAIN) { FavoriteScreen() }
        }

        navigation(route = GRAPH_SETTINGS, startDestination = ROUTE_SETTINGS_MAIN) {
            composable(ROUTE_SETTINGS_MAIN) { SettingsScreen() }
        }
    }
}


