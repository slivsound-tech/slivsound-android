package com.slivsound.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.slivsound.R

sealed class BottomTab(
    val route: String,
    val labelRes: Int,
    val icon: ImageVector,
    val selectedIcon: ImageVector? = null
) {
    data object Home : BottomTab(GRAPH_HOME, R.string.tab_home, Icons.Outlined.Home)
    data object Discover : BottomTab(GRAPH_DISCOVER, R.string.tab_discover, Icons.Outlined.Search)
    data object Favorite : BottomTab(
        GRAPH_FAVORITE,
        R.string.tab_favorite,
        Icons.Outlined.FavoriteBorder,
        Icons.Outlined.Favorite
    )

    data object Settings : BottomTab(GRAPH_SETTINGS, R.string.tab_settings, Icons.Outlined.Settings)
}

val bottomTabs = listOf(
    BottomTab.Home,
    BottomTab.Discover,
    BottomTab.Favorite,
    BottomTab.Settings
)

