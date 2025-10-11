package com.slivsound.feature.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.slivsound.R

@Preview(showBackground = true)
@Composable
fun HomeScreen() {

    Text(text = stringResource(R.string.nav_item_home), style = MaterialTheme.typography.headlineMedium)
}
