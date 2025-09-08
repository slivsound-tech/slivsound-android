package com.slivsound.ui.components.chip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slivsound.ui.theme.SlivsoundTheme

@Preview(showBackground = true)
@Composable
fun ChipPreview() {
    var selected1 by remember { mutableStateOf(false) }
    var selected2 by remember { mutableStateOf(true) }
    SlivsoundTheme {
        Column(modifier = Modifier.padding(10.dp)) {

            SelectableChip(
                text = "Android",
                selected = true,
                onSelectedChange = { newValue -> selected1 = newValue }
            )
            Spacer(Modifier.height(8.dp))
            SelectableChip(
                text = "Android",
                selected = false,
                onSelectedChange = { newValue -> selected2 = newValue }
            )
        }
    }
}