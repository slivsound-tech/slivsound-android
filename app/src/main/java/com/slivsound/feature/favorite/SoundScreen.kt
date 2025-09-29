package com.slivsound.feature.favorite

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.slivsound.R

@Composable
fun SoundScreen() {
    Text(text = stringResource(R.string.tab_sound), style = MaterialTheme.typography.headlineMedium)
}
