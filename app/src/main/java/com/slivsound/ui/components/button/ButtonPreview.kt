package com.slivsound.ui.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slivsound.ui.theme.SlivsoundTheme

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    SlivsoundTheme {
        Column(modifier = Modifier.padding(10.dp)) {
            PrimaryButton(
                text = "Primary Enabled",
                onClick = {}
            )
            Spacer(Modifier.height(8.dp))
            PrimaryButton(
                text = "Primary Loading",
                onClick = {}
            )
            Spacer(Modifier.height(8.dp))
            PrimaryButton(
                text = "Primary Disabled",
                onClick = {}
            )
            Spacer(Modifier.height(8.dp))
            ButtonOutline(
                text = "Outline Enabled",
                onClick = {}
            )
            Spacer(Modifier.height(8.dp))

            ButtonOutline(
                text = "Outline Disabled",
                onClick = {}
            )
        }
    }
}
