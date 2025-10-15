package com.slivsound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.slivsound.feature.discover.DiscoverScreen
import com.slivsound.feature.favorite.SoundScreen
import com.slivsound.feature.onboarding.presentation.question.OnboardingQuestionScreen
import com.slivsound.feature.onboarding.presentation.welcome.OnboardingWelcomeScreen
import com.slivsound.feature.settings.SettingsScreen
import com.slivsound.navigation.*
import com.slivsound.ui.theme.SlivsoundTheme

// Константы маршрутов для онбординга
const val ROUTE_ONBOARDING_WELCOME = "welcome"
const val ROUTE_ONBOARDING_QUESTION = "question"

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

                        if (route != ROUTE_ONBOARDING_WELCOME && route != ROUTE_ONBOARDING_QUESTION) {
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
                                    label = { Text(stringResource(R.string.tab_discover)) },
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
                                    label = { Text(stringResource(R.string.tab_sound)) },
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
                                    label = { Text(stringResource(R.string.tab_settings)) },
                                    colors = defaultsColors
                                )
                            }
                        }
                    }
                ) { inner ->
                    NavHost(
                        navController = navController,
                        startDestination = ROUTE_ONBOARDING_WELCOME,
                        modifier = Modifier.padding(inner)
                    ) {
                        composable(ROUTE_ONBOARDING_WELCOME) {
                            OnboardingWelcomeScreen(navController)
                        }

                        composable(ROUTE_ONBOARDING_QUESTION) {
                            OnboardingQuestionScreen(navController)
                        }

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
