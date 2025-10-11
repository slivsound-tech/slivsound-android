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
import com.slivsound.feature.discover.presentation.DiscoverScreen
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
                        val selectedColor = MaterialTheme.colorScheme.primary
                        val unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant

                        val defaultsColors = NavigationBarItemDefaults.colors(
                            selectedIconColor = selectedColor,
                            selectedTextColor = selectedColor,
                            unselectedIconColor = unselectedColor,
                            unselectedTextColor = unselectedColor,
                            indicatorColor = Color.Transparent
                        )

                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 0.dp
                        ) {

                            val discoverSelected = route?.startsWith(GRAPH_DISCOVER) == true
                            NavigationBarItem(
                                selected = discoverSelected,
                                onClick = { navController.navigate(GRAPH_DISCOVER) },
                                icon = {
                                    Icon(
                                        painter = painterResource(
                                            if (discoverSelected) R.drawable.ic_search_filled
                                            else R.drawable.ic_search
                                        ),
                                        contentDescription = stringResource(R.string.tab_discover)
                                    )
                                },
                                label = {
                                    Text(
                                        stringResource(R.string.tab_discover),
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                },
                                colors = defaultsColors
                            )
                            val soundSelected = route?.startsWith(GRAPH_SOUND) == true
                            NavigationBarItem(
                                selected = soundSelected,
                                onClick = { navController.navigate(GRAPH_SOUND) },
                                icon = {
                                    Icon(
                                        painter = painterResource(
                                            if (soundSelected) R.drawable.ic_sound_filled
                                            else R.drawable.ic_sound
                                        ),
                                        contentDescription = stringResource(R.string.tab_sound)
                                    )
                                },
                                label = {
                                    Text(
                                        stringResource(R.string.tab_sound),
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                },
                                colors = defaultsColors
                            )

                            val settingsSelected = route?.startsWith(GRAPH_SETTINGS) == true
                            NavigationBarItem(
                                selected = settingsSelected,
                                onClick = { navController.navigate(GRAPH_SETTINGS) },
                                icon = {
                                    Icon(
                                        painter = painterResource(
                                            if (settingsSelected) R.drawable.ic_settings_filled
                                            else R.drawable.ic_settings
                                        ),
                                        contentDescription = stringResource(R.string.tab_settings)
                                    )
                                },
                                label = {
                                    Text(
                                        stringResource(R.string.tab_settings),
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                },
                                colors = defaultsColors
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


