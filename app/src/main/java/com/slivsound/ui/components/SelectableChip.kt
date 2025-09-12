package com.slivsound.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slivsound.ui.theme.SlivsoundTheme

@Composable
fun SelectableChip(
    text: String,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clickable { onSelectedChange(!selected) },
        shape = RoundedCornerShape(50),
        color = if (selected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surface
        },
        contentColor = if (selected) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.onSurface
        }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChipPreview() {
    var selected1 by remember { mutableStateOf(false) }
    var selected2 by remember { mutableStateOf(true) }
    SlivsoundTheme {
        SelectableChip(
            text = "Android",
            selected = true,
            onSelectedChange = { newValue -> selected1 = newValue }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChipPreview1() {
    var selected1 by remember { mutableStateOf(false) }
    var selected2 by remember { mutableStateOf(true) }
    SlivsoundTheme {
        SelectableChip(
            text = "Android",
            selected = false,
            onSelectedChange = { newValue -> selected2 = newValue }
        )
    }
}



