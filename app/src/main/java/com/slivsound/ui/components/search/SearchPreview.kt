package com.slivsound.ui.components.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.slivsound.ui.theme.SlivsoundTheme

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    var query by remember { mutableStateOf("") }

    SlivsoundTheme {
        SearchField(
            value = query,
            onValueChange = { query = it },
            onSearch = { println("Search: $query") }
        )
    }
}