package com.slivsound.ui.components.Button

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.slivsound.R
import com.slivsound.ui.theme.SlivsoundTheme

@Composable
fun ButtonOutline(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    size: OutlineButtonSize = OutlineButtonSize.Large,
    onClick: () -> Unit,
) {
    OutlineButtonView(
        modifier = modifier,
        size = size,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null,
                modifier = Modifier.height(24.dp)
            )
        }
    }
}

@Composable
fun ButtonOutline(
    modifier: Modifier = Modifier,
    icon: Painter,
    size: OutlineButtonSize = OutlineButtonSize.Medium,
    onClick: () -> Unit,
) {
    OutlineButtonView(
        modifier = modifier,
        size = size,
        onClick = onClick
    ) {
        Icon(
            painter = icon,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ButtonOutline(
    modifier: Modifier = Modifier,
    title: String,
    size: OutlineButtonSize = OutlineButtonSize.Large,
    onClick: () -> Unit,
) {
    OutlineButtonView(
        modifier = modifier,
        size = size,
        onClick = onClick
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

enum class OutlineIconSide { Left, Right }

@Composable
fun OutlineButton(
    modifier: Modifier = Modifier,
    size: OutlineButtonSize = OutlineButtonSize.Small,
    icon: Painter,
    title: String,
    onClick: () -> Unit,
    iconSide: OutlineIconSide = OutlineIconSide.Left
) {
    OutlineButtonView(
        modifier = modifier,
        size = size,
        onClick = onClick

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (iconSide == OutlineIconSide.Left) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(4.dp))
            }

            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )

            if (iconSide == OutlineIconSide.Right) {
                Spacer(Modifier.width(4.dp))
                Icon(
                    painter = icon,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun OutlineButton(
    modifier: Modifier = Modifier,
    size: OutlineButtonSize = OutlineButtonSize.Small,
    icon: ImageVector,
    title: String,
    iconSide: OutlineIconSide = OutlineIconSide.Right,
    onClick: () -> Unit,
) {
    OutlineButtonView(
        modifier = modifier,
        size = size,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (iconSide == OutlineIconSide.Right) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.width(4.dp))
            }
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )


            if (iconSide == OutlineIconSide.Left) {
                Spacer(Modifier.width(4.dp))
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium
                )
            }


        }
    }
}


enum class OutlineButtonSize(val padding: Dp) {
    Large(12.dp),
    Medium(8.dp),
    Small(4.dp),
}

@Composable
fun OutlineButtonView(
    modifier: Modifier = Modifier,
    size: OutlineButtonSize,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(2.5.dp, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        ),
        contentPadding = PaddingValues(
            horizontal = size.padding,
            vertical = size.padding
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )  {

            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutlineVectorButtonPreview() {
    SlivsoundTheme {
        ButtonOutline(
            icon = Icons.Default.CheckCircle,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OutlinePainterButtonPreview() {
    SlivsoundTheme {
        ButtonOutline(
            icon = painterResource(R.drawable.right_1),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OutlineTitleButtonPreview() {
    SlivsoundTheme {
        ButtonOutline(
            title = "Continue",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OutlinePainterTitleButtonRightPreview() {
    SlivsoundTheme {
        OutlineButton(
            iconSide = OutlineIconSide.Right,
            icon = painterResource(R.drawable.right_1),
            title = "Continue",
            onClick = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun OutlineVectorTitleButtonLeftPreview() {
    SlivsoundTheme {
        OutlineButton(
            iconSide = OutlineIconSide.Right,
            title = "Continue",
            icon = Icons.Default.CheckCircle,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OutlinePainterTitleButtonLeftPreview() {
    SlivsoundTheme {
        OutlineButton(
            icon = painterResource(R.drawable.left_1),
            title = "Continue",
            iconSide = OutlineIconSide.Left,
            onClick = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun OutlineVectorTitleButtonRightPreview() {
    SlivsoundTheme {
        OutlineButton(
            title = "Continue",
            icon = Icons.Default.CheckCircle,
            iconSide = OutlineIconSide.Left,
            onClick = {}
        )
    }
}