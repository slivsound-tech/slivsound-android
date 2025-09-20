package com.slivsound.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    title: String,
    icon: Painter,
    iconPlacement: IconPlacement = IconPlacement.Left,
    size: ButtonSize = ButtonSize.Large,
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
            if (iconPlacement == IconPlacement.Left) {
                Icon(
                    painter = icon,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )
            if (iconPlacement == IconPlacement.Right) {
                Icon(
                    painter = icon,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

    }
}

@Composable
fun Button(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    iconPlacement: IconPlacement = IconPlacement.Left,
    size: ButtonSize = ButtonSize.Large,
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
            if (iconPlacement == IconPlacement.Left) {
                Icon(
                    imageVector = icon,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )
            if (iconPlacement == IconPlacement.Right) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = icon,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

enum class IconPlacement {
    Right, Left
}

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
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = size.padding, vertical = size.padding),
        contentAlignment = Alignment.Center
    ) {
        Row() {
            content()
        }
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
            icon = painterResource(R.drawable.right_1),
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
fun PrimaryPainterTitleButtonPreview() {
    SlivsoundTheme {
        Button(
            title = "Continue",
            icon = painterResource(R.drawable.right_1),
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
fun PrimaryPainterTitleButtonLeftPreview() {
    SlivsoundTheme {
        Button(
            title = "Continue",
            icon = painterResource(R.drawable.left_1),
            iconPlacement = IconPlacement.Left,
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
            iconPlacement = IconPlacement.Left,
            onClick = {}
        )
    }
}

@Composable
fun ButtonOutline(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    size: ButtonSize = ButtonSize.Large,
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
    size: ButtonSize = ButtonSize.Small,
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
    size: ButtonSize = ButtonSize.Medium,
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

@Composable
fun ButtonOutline(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    size: ButtonSize = ButtonSize.Large,
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
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,

                )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = icon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ButtonOutline(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    size: ButtonSize = ButtonSize.Large,
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
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = icon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun OutlineButtonLeft(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    size: ButtonSize = ButtonSize.Large,
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

                painter = icon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,

                )

        }
    }
}

@Composable
fun OutlineButtonLeft(
    modifier: Modifier = Modifier,
    size: ButtonSize = ButtonSize.Large,
    icon: ImageVector,
    title: String,
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
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,

                )

        }
    }
}

@Composable
fun OutlineButtonView(
    modifier: Modifier = Modifier,
    size: ButtonSize,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onClick)
            .background(Color.Transparent)
            .border(
                width = 2.5.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = size.padding, vertical = size.padding),
        contentAlignment = Alignment.Center
    ) {
        Row() {

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
fun OutlineVectorTitleButtonPreview() {
    SlivsoundTheme {
        ButtonOutline(
            title = "Continue",
            icon = Icons.Default.CheckCircle,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OutlinePainterTitleButtonPreview() {
    SlivsoundTheme {
        ButtonOutline(
            title = "Continue",
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
fun OutlinePainterTitleButtonLeftPreview() {
    SlivsoundTheme {
        OutlineButtonLeft(
            title = "Continue",
            icon = painterResource(R.drawable.left_1),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OutlineVectorTitleButtonLeftPreview() {
    SlivsoundTheme {
        OutlineButtonLeft(
            title = "Continue",
            icon = Icons.Default.CheckCircle,
            onClick = {}
        )
    }
}





