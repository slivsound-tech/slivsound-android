package com.slivsound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.slivsound.feature.discover.DiscoverScreen
import com.slivsound.feature.favorite.SoundScreen
import com.slivsound.feature.settings.SettingsScreen
import com.slivsound.navigation.GRAPH_DISCOVER
import com.slivsound.navigation.GRAPH_SETTINGS
import com.slivsound.navigation.GRAPH_SOUND
import com.slivsound.navigation.ROUTE_DISCOVER_MAIN
import com.slivsound.navigation.ROUTE_SETTINGS_MAIN
import com.slivsound.navigation.ROUTE_SOUND_MAIN
import com.slivsound.ui.theme.SlivsoundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SlivsoundTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        val backStack by navController.currentBackStackEntryAsState()
                        val route = backStack?.destination?.route
                        val selected = MaterialTheme.colorScheme.primary
                        val unselected = MaterialTheme.colorScheme.onSurfaceVariant
                        val ItemDefaultsColors = NavigationBarItemDefaults.colors(
                            selectedIconColor = selected,
                            selectedTextColor = selected,
                            unselectedIconColor = unselected,
                            unselectedTextColor = unselected,
                            indicatorColor = Color.Transparent
                        )

                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 0.dp
                        ) {
                            NavigationBarItem(
                                selected = route?.startsWith("discover") == true,
                                onClick = { navController.navigate("discover") },
                                icon = {
                                    Icon(
                                        painterResource(R.drawable.ic_search),
                                        contentDescription = null
                                    )
                                },
                                label = { Text(stringResource(R.string.tab_discover)) },
                                colors = ItemDefaultsColors
                            )

                            NavigationBarItem(
                                selected = route?.startsWith("sound") == true,
                                onClick = { navController.navigate("sound") },
                                icon = {
                                    Icon(
                                        painterResource(R.drawable.ic_sound),
                                        contentDescription = null
                                    )
                                },
                                label = { Text(stringResource(R.string.tab_sound)) },
                                colors = ItemDefaultsColors
                            )
                            NavigationBarItem(
                                selected = route?.startsWith("settings") == true,
                                onClick = { navController.navigate("settings") },
                                icon = {
                                    Icon(
                                        painterResource(R.drawable.ic_settings),
                                        contentDescription = null
                                    )
                                },
                                label = { Text(stringResource(R.string.tab_settings)) },
                                colors = ItemDefaultsColors
                            )
                        }
                    }
                ) { inner ->
                    NavHost(
                        navController = navController,
                        startDestination = GRAPH_DISCOVER,
                        modifier = Modifier.padding(inner)
                    ) {
                        navigation(route = GRAPH_DISCOVER, startDestination = ROUTE_DISCOVER_MAIN) {
                            composable(ROUTE_DISCOVER_MAIN) { DiscoverScreen() }
                        }
                        navigation(route = GRAPH_SOUND, startDestination = ROUTE_SOUND_MAIN) {
                            composable(ROUTE_SOUND_MAIN) { SoundScreen() }
                        }
                        navigation(route = GRAPH_SETTINGS, startDestination = ROUTE_SETTINGS_MAIN) {
                            composable(ROUTE_SETTINGS_MAIN) { SettingsScreen() }
                        }
                    }
                }
            }
        }
    }
}


