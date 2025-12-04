package com.slivsound.feature.sound.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.slivsound.ui.components.BadgeTime

@Composable
fun TimeOptionals(
    onTimeSelected: (Int) -> Unit
) {
    Row {
        listOf(10, 15, 20, 30, 60).forEach { minutes ->
          BadgeTime(
                    title = "$minutes min",
                    modifier = Modifier

                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceDim)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = MaterialTheme.shapes.medium
                        ).padding(horizontal = 19.dp, vertical = 12.dp)

                        .clickable { onTimeSelected(minutes) }
                )
            Spacer(Modifier.width(4.dp))
        }
    }
}