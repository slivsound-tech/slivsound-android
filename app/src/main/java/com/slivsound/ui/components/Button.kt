package com.slivsound.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun Button(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    size: ButtonSize = ButtonSize.Large,
    onClick: () -> Unit,
) {
    ButtonView(
        modifier = modifier,
        size = size,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null,
            modifier = Modifier.height(24.dp)
        )
    }
}

@Composable
fun Button(
    modifier: Modifier = Modifier,
    size: ButtonSize = ButtonSize.Large,
    icon: Painter,
    onClick: () -> Unit,
) {
    ButtonView(
        modifier = modifier,
        size = size,
        onClick = onClick
    ) {
        Icon(
            painter = icon,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun Button(
    modifier: Modifier = Modifier,
    title: String,
    size: ButtonSize = ButtonSize.Large,
    onClick: () -> Unit,
) {
    ButtonView(
        modifier = modifier,
        size = size,
        onClick = onClick
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
fun Button(
    modifier: Modifier = Modifier,
    size: ButtonSize = ButtonSize.Small,
    icon: Painter,
    title: String,
    onClick: () -> Unit,
    iconSide: IconSide = IconSide.Left
) {
    ButtonView(
        modifier = modifier,
        size = size,
        onClick = onClick

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (iconSide == IconSide.Left) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(4.dp))
            }

            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )

            if (iconSide == IconSide.Right) {
                Spacer(Modifier.width(4.dp))
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun Button(
    modifier: Modifier = Modifier,
    size: ButtonSize = ButtonSize.Medium,
    icon: ImageVector,
    title: String,
    iconSide: IconSide = IconSide.Right,
    onClick: () -> Unit,
) {
    ButtonView(
        modifier = modifier,
        size = size,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (iconSide == IconSide.Right) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(4.dp))
            }

            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )

            if (iconSide == IconSide.Left) {
                Spacer(Modifier.width(4.dp))
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

enum class IconSide { Left, Right }

enum class ButtonSize(val padding: Dp) {
    Large(12.dp),
    Medium(8.dp),
    Small(4.dp),
}

@Composable
private fun ButtonView(
    modifier: Modifier = Modifier,
    size: ButtonSize,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.38f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.38f),
        ),
        contentPadding = PaddingValues(
            horizontal = size.padding,
            vertical = size.padding
        ),

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

@Composable
fun ButtonPlus(
    icon: Painter,
    onAddClick: () -> Unit,
) {
    IconButton(onClick = { onAddClick() }) {
        Icon(
            painter = icon,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryVectorButtonPreview() {
    SlivsoundTheme {
        Button(
            icon = Icons.Default.CheckCircle,
            onClick = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PrimaryPainterButtonPreview() {
    SlivsoundTheme {
        Button(
            icon = painterResource(R.drawable.ic_right),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryTitleButtonPreview() {
    SlivsoundTheme {
        Button(
            title = "Continue",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryVectorTitleButtonPreview() {
    SlivsoundTheme {
        Button(
            title = "Continue",
            icon = Icons.Default.CheckCircle,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryPainterTitleButtonLeftPreview() {
    SlivsoundTheme {
        Button(
            icon = painterResource(R.drawable.ic_left),
            title = "Continue",
            iconSide = IconSide.Left,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryPainterTitleButtonRightPreview() {
    SlivsoundTheme {
        Button(
            iconSide = IconSide.Right,
            icon = painterResource(R.drawable.ic_right),
            title = "Continue",
            onClick = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PrimaryVectorTitleButtonLeftPreview() {
    SlivsoundTheme {
        Button(
            title = "Continue",
            icon = Icons.Default.CheckCircle,
            iconSide = IconSide.Left,
            onClick = {}
        )
    }
}
