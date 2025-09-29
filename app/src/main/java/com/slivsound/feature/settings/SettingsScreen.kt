package com.slivsound.feature.settings

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.slivsound.R

@Composable
fun SettingsScreen() {

    Text(text = stringResource(R.string.tab_settings), style = MaterialTheme.typography.headlineMedium)

}