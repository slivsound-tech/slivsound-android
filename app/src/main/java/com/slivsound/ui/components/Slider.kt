package com.slivsound.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Slider(
    modifier: Modifier = Modifier,
    value: Float,
    onValueChange: (Float) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val activePurple = MaterialTheme.colorScheme.primary
    val inactiveGray = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.24f)

    val colors = SliderDefaults.colors(
        thumbColor = Color.White,
        activeTrackColor = activePurple,
        inactiveTrackColor = inactiveGray,
        disabledThumbColor = Color.LightGray,
        disabledActiveTrackColor = activePurple.copy(alpha = 0.38f),
        disabledInactiveTrackColor = inactiveGray.copy(alpha = 0.38f)
    )

    androidx.compose.material3.Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = 0f..100f,
        modifier = modifier.fillMaxWidth(),
        interactionSource = interactionSource,
        colors = colors,
        track = { sliderState ->
            SliderDefaults
                .Track(
                    sliderState = sliderState,
                    enabled = true,
                    colors = colors,
                    modifier = Modifier.height(2.dp)
                )
        },
        thumb = {
            SliderDefaults.Thumb(
                interactionSource = interactionSource,
                colors = colors,
                thumbSize = DpSize(15.dp, 15.dp)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SliderPreview() {
    Slider(
        value = 50f,
        onValueChange = {}
    )
}