package com.slivsound.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.slivsound.R
import com.slivsound.ui.theme.SlivsoundTheme

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    selected: Boolean,
    size: ChipSize = ChipSize.Large,
    onSelectedChange: (Boolean) -> Unit,
    onClick: () -> Unit,
) {
    ChipView(
        modifier = modifier,
        size = size,
        selected = selected,
        onSelectedChange = onSelectedChange,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            tint = if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurface,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    icon: Painter,
    size: ChipSize = ChipSize.Large,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    onClick: () -> Unit,
) {
    ChipView(
        modifier = modifier,
        size = size,
        selected = selected,
        onSelectedChange = onSelectedChange,
        onClick = onClick
    ) {
        Icon(
            painter = icon,
            tint = if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurface,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    title: String,
    size: ChipSize = ChipSize.Large,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    onClick: () -> Unit,
) {
    ChipView(
        modifier = modifier,
        size = size,
        selected = selected,
        onSelectedChange = onSelectedChange,
        onClick = onClick
    ) {
        Text(
            text = title,
            color = if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    size: ChipSize = ChipSize.Large,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    chipIconSide: ChipIconSide = ChipIconSide.Left,
    onClick: () -> Unit,
) {
    ChipView(
        modifier = modifier,
        size = size,
        selected = selected,
        onSelectedChange = onSelectedChange,
        onClick = onClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (chipIconSide == ChipIconSide.Left) {
                Icon(
                    imageVector = icon,
                    tint = if (selected) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onSurface,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(Modifier.width(4.dp))
            }
            Text(
                text = title,
                color = if (selected) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(Modifier.width(4.dp))

            if (chipIconSide == ChipIconSide.Right) {
                Icon(
                    imageVector = icon,
                    tint = if (selected) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onSurface,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    size: ChipSize = ChipSize.Large,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    chipIconSide: ChipIconSide = ChipIconSide.Right,
    onClick: () -> Unit,
) {
    ChipView(
        modifier = modifier,
        size = size,
        selected = selected,
        onSelectedChange = onSelectedChange,
        onClick = onClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (chipIconSide == ChipIconSide.Right) {
                Text(
                    text = title,
                    color = if (selected) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(Modifier.width(4.dp))
            }
            Icon(
                painter = icon,
                tint = if (selected) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onSurface,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(4.dp))
            if (chipIconSide == ChipIconSide.Left) {
                Text(
                    text = title,
                    color = if (selected) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

enum class ChipIconSide { Left, Right }

enum class ChipSize(val padding: Dp) {
    Large(12.dp),
    Medium(8.dp),
    Small(4.dp)
}

@Composable
private fun ChipView(
    modifier: Modifier = Modifier,
    size: ChipSize,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val container = if (selected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.surfaceVariant
    val contentColor = if (selected) MaterialTheme.colorScheme.onPrimary
    else MaterialTheme.colorScheme.onSurface

    Button(
        onClick = {
            onSelectedChange(!selected)
            onClick()
        },
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = container,
            contentColor = contentColor,
            disabledContainerColor = container.copy(alpha = 0.38f),
            disabledContentColor = contentColor.copy(alpha = 0.38f)
        ),
        contentPadding = PaddingValues(
            horizontal = size.padding,
            vertical = size.padding
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp,
            disabledElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            content()
        }
    }
}

@Preview(showBackground = true, name = "Chip • ImageVector only")
@Composable
fun Preview_Chip_Vector() {
    SlivsoundTheme {
        var selected by remember { mutableStateOf(false) }
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Chip(
                icon = Icons.Default.CheckCircle,
                size = ChipSize.Large,
                selected = selected,
                onSelectedChange = { selected = it },
                onClick = {}
            )
            Chip(
                icon = Icons.Default.CheckCircle,
                size = ChipSize.Large,
                selected = !selected,
                onSelectedChange = { selected = !it },
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Chip • Painter only")
@Composable
fun Preview_Chip_Painter() {
    SlivsoundTheme {
        var selected by remember { mutableStateOf(true) }
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Chip(
                icon = painterResource(R.drawable.ic_right),
                size = ChipSize.Large,
                selected = selected,
                onSelectedChange = { selected = it },
                onClick = {}
            )
            Chip(
                icon = painterResource(R.drawable.ic_right),
                size = ChipSize.Large,
                selected = !selected,
                onSelectedChange = { selected = !it },
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Chip • Title only")
@Composable
fun Preview_Chip_Title() {
    SlivsoundTheme {
        var selected by remember { mutableStateOf(false) }
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Chip(
                title = "Continue",
                size = ChipSize.Large,
                selected = selected,
                onSelectedChange = { selected = it },
                onClick = {}
            )
            Chip(
                title = "Continue",
                size = ChipSize.Large,
                selected = !selected,
                onSelectedChange = { selected = !it },
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "ChipTitleImageVector (left)")
@Composable
fun PreviewChipTitleVectorRight() {
    SlivsoundTheme {
        var selected by remember { mutableStateOf(true) }
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Chip(
                chipIconSide = ChipIconSide.Left,
                icon = Icons.Default.CheckCircle,
                title = "Continue",
                size = ChipSize.Large,
                selected = selected,
                onSelectedChange = { selected = it },
                onClick = {}
            )
            Chip(
                chipIconSide = ChipIconSide.Right,
                icon = Icons.Default.CheckCircle,
                title = "Continue",
                size = ChipSize.Medium,
                selected = !selected,
                onSelectedChange = { selected = !it },
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "ChipTitlePainter (right)")
@Composable
fun PreviewChipTitlePainterRight() {
    SlivsoundTheme {
        var selected by remember { mutableStateOf(false) }
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Chip(
                chipIconSide = ChipIconSide.Right,
                icon = painterResource(R.drawable.ic_right),
                title = "Continue",
                size = ChipSize.Large,
                selected = selected,
                onSelectedChange = { selected = it },
                onClick = {}
            )
            Chip(
                chipIconSide = ChipIconSide.Left,
                icon = painterResource(R.drawable.ic_left),
                title = "Continue",
                size = ChipSize.Small,
                selected = !selected,
                onSelectedChange = { selected = !it },
                onClick = {}
            )
        }
    }
}

