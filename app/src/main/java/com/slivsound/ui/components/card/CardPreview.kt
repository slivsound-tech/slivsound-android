package com.slivsound.ui.components.card

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.slivsound.ui.theme.SlivsoundTheme

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    SlivsoundTheme {
        Column {
            CardView(
                title = "Card content ",
                subtitle = " subtitle",
                onClick = {})

        }
    }
}
