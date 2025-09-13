package com.slivsound.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderPositions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderView(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val colors = SliderColors(
        thumbColor = Color(0xFF1611680),
        activeTrackColor = Color(0xFF1611680),
        activeTickColor = Color(0xFF1611680),
        inactiveTrackColor = Color(0xFF1611680),
        inactiveTickColor = Color(0xFF1611680),
        disabledThumbColor = Color(0xFF1611680),
        disabledActiveTrackColor = Color(0xFF1611680),
        disabledActiveTickColor = Color(0xFF1611680),
        disabledInactiveTrackColor = Color(0xFF1611680),
        disabledInactiveTickColor = Color(0xFF1611680),
    )

    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = 0f..100f,
        modifier = modifier,
        colors = colors,
        track = { sliderState ->
            SliderDefaults.Track(
                colors = colors,
                enabled = true,
                sliderState = sliderState,
                modifier = Modifier.height(2.dp)
            )
        },
        thumb = {
            SliderDefaults.Thumb(
                interactionSource = interactionSource,
                colors = colors,
                thumbSize = DpSize(20.dp, 20.dp)
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun SliderPreview() {
    SliderView(
        value = 50f,
        onValueChange = {}
    )
}