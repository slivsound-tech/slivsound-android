package com.slivsound.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slivsound.R
import com.slivsound.ui.theme.SlivsoundTheme

@Composable
fun Badge(
    title: String,
    modifier: Modifier = Modifier
) {
    BadgeView(modifier = modifier) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun BadgeTime(
    title: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier

    ){
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}


@Composable
fun Badge(
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    BadgeView(modifier = modifier) {
        Icon(
            imageVector = icon,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun Badge(
    icon: Painter,
    modifier: Modifier = Modifier
) {
    BadgeView(modifier = modifier) {
        Icon(
            painter = icon,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
    }
}
@Composable
fun Badge1(
    icon: Painter,
) {
        Icon(
            painter = icon,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
}

@Composable
fun Badge(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    BadgeView(modifier = modifier) {
        Icon(
            imageVector = icon,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun Badge(
    title: String,
    icon: Painter,
    modifier: Modifier = Modifier
) {
    BadgeView(modifier = modifier) {
        Icon(
            painter = icon,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
private fun BadgeView(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.medium
            )
            .background(MaterialTheme.colorScheme.surfaceDim)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BadgeTextPreview() {
    SlivsoundTheme {
        Badge(title = "Badge")
    }
}

@Preview(showBackground = true)
@Composable
fun BadgeIconVectorPreview() {
    SlivsoundTheme {
        Badge(icon = Icons.Default.CheckCircle)
    }
}

@Preview(showBackground = true)
@Composable
fun BadgeIconPainterPreview() {
    SlivsoundTheme {
        Badge(icon = painterResource(R.drawable.ic_music))
    }
}

@Preview(showBackground = true)
@Composable
fun BadgeIconVectorTextPreview() {
    SlivsoundTheme {
        Badge(
            title = "Badge",
            icon = Icons.Default.CheckCircle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BadgeIconPainterTextPreview() {
    SlivsoundTheme {
        Badge(
            title = "Badge",
            icon = painterResource(R.drawable.ic_music)
        )
    }
}