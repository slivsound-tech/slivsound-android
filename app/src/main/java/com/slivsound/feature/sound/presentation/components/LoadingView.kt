package com.slivsound.feature.sound.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingView() = Column(
    modifier = Modifier
        .fillMaxSize(),
    verticalArrangement = Arrangement.Center
) {
    Text("Loading...")
}