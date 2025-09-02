package com.slivsound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.slivsound.featurename.presentation.NameScreen
import com.slivsound.ui.theme.SlivsoundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlivsoundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NameScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
