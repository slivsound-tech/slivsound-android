package com.slivsound.feature.discover

import androidx.compose.material3.MaterialTheme
import com.slivsound.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun DiscoverScreen() {
    Text(text =  stringResource(id = R.string.tab_discover), style = MaterialTheme.typography.headlineMedium)
}