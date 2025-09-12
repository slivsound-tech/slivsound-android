package com.slivsound.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slivsound.ui.theme.SlivsoundTheme

@Composable
fun BadgePillTransparent(
    text: String = "Badge",
    modifier: Modifier = Modifier,
    icon: ImageVector? = Icons.Outlined.Build,
    borderColor: Color = MaterialTheme.colorScheme.outline,       // тёмная обводка
    contentColor: Color = MaterialTheme.colorScheme.onSurface,    // цвет иконки/текста
    borderWidth: Dp = 6.dp,
    contentAlpha: Float = 0.28f,                                  // полупрозрачность контента
) {
    val tint = contentColor.copy(alpha = contentAlpha)

    Row(
        modifier = modifier
            .height(84.dp)
            .clip(CircleShape)
            .border(BorderStroke(borderWidth, borderColor), CircleShape)
            .padding(horizontal = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(22.dp)
            )
            Spacer(Modifier.width(10.dp))
        }
        Text(
            text = text,
            color = Color.Gray,
            fontSize = 48.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BadgeLabelPreview() {
    SlivsoundTheme {
        Box(Modifier.padding(16.dp)) {
            BadgePillTransparent( text = "Badge")
        }
    }
}