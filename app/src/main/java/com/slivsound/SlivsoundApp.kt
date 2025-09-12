package com.slivsound

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.slivsound.navigation.BottomBar
import com.slivsound.navigation.RootNavGraph

@Composable
fun SlivsoundApp() {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomBar(navController) }) { inner ->
        RootNavGraph(navController, Modifier.padding(inner))
    }
}
